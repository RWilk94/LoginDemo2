package rwilk.logindemo2.config.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rwilk.logindemo2.model.JWTUser;

import static rwilk.logindemo2.config.security.SecurityConstants.EXPIRATION_TIME;
import static rwilk.logindemo2.config.security.SecurityConstants.HEADER_STRING;
import static rwilk.logindemo2.config.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.config.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      JWTUser JWTUser = new ObjectMapper().readValue(request.getInputStream(), JWTUser.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(JWTUser.getUsername(), JWTUser.getPassword()));
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

    ZonedDateTime expirationTimeUTC =
        ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.SECONDS); //EXPIRATION_TIME, ChronoUnit.MILLIS);
    String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername()) // TODO add expiration time to body/subject
        .setExpiration(Date.from(expirationTimeUTC.toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();

    System.out.println(token);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("token", token);

    response.getWriter().write(jsonObject.toJSONString());
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
