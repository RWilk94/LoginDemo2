package rwilk.logindemo2.config.security;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rwilk.logindemo2.service.IUserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static rwilk.logindemo2.config.security.SecurityConstants.EXPIRATION_TIME;
import static rwilk.logindemo2.config.security.SecurityConstants.HEADER_STRING;
import static rwilk.logindemo2.config.security.SecurityConstants.SECRET;

public class JWTAuthorizationFilterTest {

  @InjectMocks
  private JWTAuthorizationFilter jwtAuthorizationFilter;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private IUserService userService;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain chain;

  @Mock
  private Authentication authResult;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldFilterInternalWhenTokenIsNull() throws IOException, ServletException {
    when(request.getHeader(HEADER_STRING)).thenReturn(null);
    jwtAuthorizationFilter.doFilterInternal(request, response, chain);
  }

  @Test
  public void shouldFilterInternalWhenTokenIsNotNull() throws IOException, ServletException {
    // When
    when(authResult.getPrincipal()).thenReturn(new User("username", "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));

    // When
    ZonedDateTime expirationTimeUTC =
        ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.SECONDS);
    String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
        .setExpiration(Date.from(expirationTimeUTC.toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET)
        .compact();
    when(request.getHeader(HEADER_STRING)).thenReturn("Bearer " + token);

    // When
    UserDetails userDetails = new org.springframework.security.core.userdetails.User("username", "password",
        AuthorityUtils.createAuthorityList("ROLE_USER"));
    when(userService.loadUserByUsername("username")).thenReturn(userDetails);

    jwtAuthorizationFilter.doFilterInternal(request, response, chain);
    verify(chain, times(1)).doFilter(request, response);
  }
}
