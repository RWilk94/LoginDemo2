package rwilk.logindemo2.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

  private EmailValidator emailValidator;

  @Before
  public void setup() {
    emailValidator = new EmailValidator();
  }

  @Test
  public void isValidEmail() {
    assertTrue(emailValidator.isValid("valid@email.em", null));
  }

  @Test
  public void isInvalidEmail() {
    assertFalse(emailValidator.isValid("invalidemail.em", null));
  }

}
