package rwilk.logindemo2.rest.exception;

public class DifferentPasswordAndConfirmPasswordException extends RuntimeException {

  public DifferentPasswordAndConfirmPasswordException() {
  }

  public DifferentPasswordAndConfirmPasswordException(String message) {
    super(message);
  }

  public DifferentPasswordAndConfirmPasswordException(String message, Throwable cause) {
    super(message, cause);
  }

  public DifferentPasswordAndConfirmPasswordException(Throwable cause) {
    super(cause);
  }

  public DifferentPasswordAndConfirmPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
