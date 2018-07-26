package rwilk.logindemo2.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsernameValidatorTest {

  private UsernameValidator usernameValidator;

  @Before
  public void before() {
    usernameValidator = new UsernameValidator();
  }

  @Test
  public void isValidUsername() {
    Assert.assertTrue(usernameValidator.isValid("Username", null));
    Assert.assertTrue(usernameValidator.isValid("User12", null));
    Assert.assertTrue(usernameValidator.isValid("us12er", null));
  }

  @Test
  public void isInvalidUsername() {
    Assert.assertFalse(usernameValidator.isValid("User!name12", null));
  }

}
