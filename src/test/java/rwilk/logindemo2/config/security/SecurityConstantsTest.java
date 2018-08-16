package rwilk.logindemo2.config.security;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import static rwilk.logindemo2.config.security.SecurityConstants.EXPIRATION_TIME;

public class SecurityConstantsTest {

  //@InjectMocks
  //public SecurityConstants securityConstants;

  @Test
  public void checkExpirationTime() {

    Assert.assertEquals(3600L, EXPIRATION_TIME);

    //EXPIRATION_TIME

  }

}
