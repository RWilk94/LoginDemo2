package rwilk.logindemo2.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import rwilk.logindemo2.model.ApplicationUser;
import rwilk.logindemo2.service.CustomUserDetailsService;

import static rwilk.logindemo2.security.SecurityConstants.HEADER_STRING;
import static rwilk.logindemo2.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private final CustomUserDetailsService customUserDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
    super(authenticationManager);
    this.customUserDetailsService = customUserDetailsService;
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
    if (token == null) {
      return null;
    }
    String username = Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))//remove Bearer
        .getBody()
        .getSubject();

    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
    ApplicationUser applicationUser = customUserDetailsService.loadApplicationUserByUsername(username);
    return username != null ? new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()) : null;
  }
}
