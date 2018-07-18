package rwilk.logindemo2.service;

import rwilk.logindemo2.model.User;

import java.util.List;

public interface UserService {

    User getUserByUsername(String login);

    User registerUser(User user);

    List<User> findAllUsers();

}
