package rwilk.logindemo2.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rwilk.logindemo2.model.ApplicationUser;

import static rwilk.logindemo2.security.SecurityConstants.EXPIRATION_TIME;
import static rwilk.logindemo2.security.SecurityConstants.HEADER_STRING;
import static rwilk.logindemo2.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword()));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(100, ChronoUnit.SECONDS); //EXPIRATION_TIME, ChronoUnit.MILLIS);
    String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
        .setExpiration(Date.from(expirationTimeUTC.toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    response.getWriter().write(token);
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
