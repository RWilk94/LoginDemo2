package rwilk.logindemo2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rwilk.logindemo2.model.User;
import rwilk.logindemo2.service.IUserService;

import java.util.Map;

import javax.validation.Valid;

@RestController
public class UserController {

  @Autowired
  private IUserService IUserService;

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public User registerUser(@Valid @RequestBody User user) {
    return IUserService.registerUser(user);
  }

  @PreAuthorize("hasAnyRole('USER')")
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<User> getUser(@RequestBody Map<String, String> json) {
    String username = json.get("username");
    if (username == null || username.isEmpty()) {
      throw new RuntimeException("Empty username :(");
    }
    return new ResponseEntity<>(IUserService.getUserByUsername(username), HttpStatus.OK);
  }

  @PreAuthorize("hasAnyRole('USER')")
  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    if (user == null) {
      throw new RuntimeException("Empty user :(");
    }
    return new ResponseEntity<>(IUserService.updateUser(user), HttpStatus.OK);
  }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody JWTUser jwtUser) {



        return new ResponseEntity<String>("dupa", HttpStatus.OK);
    }*/

}
