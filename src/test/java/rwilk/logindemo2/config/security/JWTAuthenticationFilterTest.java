package rwilk.logindemo2.config.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JWTAuthenticationFilterTest {

  @InjectMocks
  private JWTAuthenticationFilter jwtAuthenticationFilter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private FilterChain chain;

  @Mock
  private Authentication authResult;

  @Mock
  private PrintWriter printWriter;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldAttemptAuthentication() throws IOException {
    String inputStreamString = "{\n"
        + "\t\"username\": \"wolf\",\n"
        + "\t\"password\": \"password\"\n"
        + "}";
    ServletInputStream inputStream = new MockServletInputStream(IOUtils.toInputStream(inputStreamString));
    doReturn(inputStream).when(request).getInputStream();
    jwtAuthenticationFilter.attemptAuthentication(request, response);
  }

  @Test(expected = RuntimeException.class)
  public void shouldThrowExceptionWhenAttemptAuthentication() throws IOException {
    IOException exception = new IOException();
    when(request.getInputStream()).thenThrow(exception);
    jwtAuthenticationFilter.attemptAuthentication(request, response);
  }

  @Test
  public void shouldSuccessfulAuthentication() throws IOException, ServletException {
    when(authResult.getPrincipal()).thenReturn(new User("username", "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
    when(response.getWriter()).thenReturn(printWriter);

    jwtAuthenticationFilter.successfulAuthentication(request, response, chain, authResult);

    verify(response, times(1)).addHeader(any(), any());
  }

}
