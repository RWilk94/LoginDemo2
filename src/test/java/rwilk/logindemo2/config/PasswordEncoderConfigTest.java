package rwilk.logindemo2.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class PasswordEncoderConfigTest {

  @InjectMocks
  PasswordEncoderConfig passwordEncoderConfig;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldEncodePassword() {
    String password = "password";
    String encodePassword = passwordEncoderConfig.passwordEncoder().encode(password);
    Assert.assertNotEquals(password, encodePassword);
  }

  @Test
  public void shouldMatchesPassword() {
    String password = "password";
    String encodePassword = passwordEncoderConfig.passwordEncoder().encode(password);
    Assert.assertTrue(passwordEncoderConfig.passwordEncoder().matches(password, encodePassword));
  }

}
