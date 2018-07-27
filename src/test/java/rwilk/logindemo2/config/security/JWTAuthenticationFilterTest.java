package rwilk.logindemo2.config.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class JWTAuthenticationFilterTest {

  private String inputStreamString = "{\n"
      + "\t\"username\": \"wolf\",\n"
      + "\t\"password\": \"password\"\n"
      + "}";

  @InjectMocks
  private JWTAuthenticationFilter jwtAuthenticationFilter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private AuthenticationManager authenticationManager;

  @Before
  public void setup() throws IOException {
    MockitoAnnotations.initMocks(this);
    //request = Mockito.mock(HttpServletRequest.class);
    //inputStream.getBytes().
    //when(request.getInputStream()).thenReturn(IOUtils.toInputStream(inputStream));

  }

  @Test
  public void shouldAttemptAuthentication() throws IOException {
    ServletInputStream inputStream = new MockServletInputStream(IOUtils.toInputStream(inputStreamString));
    doReturn(inputStream).when(request).getInputStream();

    jwtAuthenticationFilter.attemptAuthentication(request,response);
  }

}
