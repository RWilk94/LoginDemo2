package rwilk.logindemo2.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<UserErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {

    UserErrorResponse errorResponse = new UserErrorResponse();
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(exception.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<UserErrorResponse> handleInvalidUserDataException(RuntimeException exception) {

    UserErrorResponse errorResponse = new UserErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage(exception.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    UserErrorResponse errorResponse = new UserErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage("Validation Failed");
    errorResponse.setTimeStamp(System.currentTimeMillis());
    errorResponse.setDetails(exception.getBindingResult().toString());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
