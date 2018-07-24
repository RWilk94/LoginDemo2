package rwilk.logindemo2.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

  String message() default "{Invalid email.}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
