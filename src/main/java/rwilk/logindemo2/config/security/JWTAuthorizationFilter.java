package rwilk.logindemo2.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import rwilk.logindemo2.model.JWTUser;
import rwilk.logindemo2.service.IUserService;

import static rwilk.logindemo2.config.security.SecurityConstants.HEADER_STRING;
import static rwilk.logindemo2.config.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.config.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private final IUserService IUserService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, IUserService IUserService) {
    super(authenticationManager);
    this.IUserService = IUserService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String header = request.getHeader(HEADER_STRING);
    if ((header == null) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationToken(request);
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    String username = Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))//remove Bearer
        .getBody()
        .getSubject();

    UserDetails userDetails = IUserService.loadUserByUsername(username);
    JWTUser JWTUser = IUserService.loadApplicationUserByUsername(username);
    return username != null ? new UsernamePasswordAuthenticationToken(JWTUser, null, userDetails.getAuthorities()) : null;
  }
}
