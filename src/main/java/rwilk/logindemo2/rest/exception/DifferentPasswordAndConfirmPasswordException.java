package rwilk.logindemo2.rest.exception;

public class DifferentPasswordAndConfirmPasswordException extends RuntimeException {

  public DifferentPasswordAndConfirmPasswordException(String message) {
    super(message);
  }
}
