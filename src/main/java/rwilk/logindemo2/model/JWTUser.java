package rwilk.logindemo2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTUser {

  private String username;
  private String password;
}
