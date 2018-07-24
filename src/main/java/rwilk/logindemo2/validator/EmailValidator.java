package rwilk.logindemo2.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

  @Override
  public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
    return Pattern.matches("[a-z0-9._%+-]+[@][a-z0-9.-]+[.][a-z]{2,4}", email);
  }
}
