package rwilk.logindemo2.rest.exception;

import lombok.Data;

/**
 * Model class for Exception Response.
 */
@Data
public class UserErrorResponse {

  private int status;
  private String message;
  private String details;
  private long timeStamp;

}
