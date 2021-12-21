package am.smartcafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelAlreadyExistException extends Exception {
  public ModelAlreadyExistException() {}

  public ModelAlreadyExistException(String message) {
    super(message);
  }

  public ModelAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelAlreadyExistException(Throwable cause) {
    super(cause);
  }

  public ModelAlreadyExistException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
