package rwilk.logindemo2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.service.UserService;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> getUser(@RequestBody Map<String, String> json) {
        String username = json.get("username");
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Empty username :(");
        }

        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody JWTUser jwtUser) {



        return new ResponseEntity<String>("dupa", HttpStatus.OK);
    }*/

}
