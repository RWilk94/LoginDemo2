package rwilk.logindemo2.rest.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

public class RestExceptionHandlerTest {

  @InjectMocks
  private RestExceptionHandler restExceptionHandler;

  @Mock
  private BindingResult bindingResult;

  @Mock
  private MethodParameter methodParameter;

  @Mock
  private WebRequest webRequest;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldHandleDifferentPasswordAndConfirmPasswordException() {
    DifferentPasswordAndConfirmPasswordException exception = new DifferentPasswordAndConfirmPasswordException("");
    restExceptionHandler.handleDifferentPasswordAndConfirmPasswordException(exception);
  }

  @Test
  public void shouldHandleMethodArgumentNotValid() {
    MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);
    restExceptionHandler.handleMethodArgumentNotValid(exception, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
  }

  @Test
  public void shouldHandleSQLIntegrityConstraintViolationException() {
    ConstraintViolationException exception = new ConstraintViolationException("Message", new SQLException(), "Name");
    restExceptionHandler.handleSQLIntegrityConstraintViolationException(exception);
  }
}