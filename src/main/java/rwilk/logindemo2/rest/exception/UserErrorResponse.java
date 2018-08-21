package rwilk.logindemo2.rest.exception;

import lombok.Data;

/**
 * Module class for Exception Response.
 */
@Data
public class UserErrorResponse {

  private int status;
  private String message;
  private String details;
  private long timeStamp;

}
