package rwilk.logindemo2.service;

import rwilk.logindemo2.model.JWTUser;
import rwilk.logindemo2.model.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {

  User registerUser(User user);

  @Override
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  JWTUser loadApplicationUserByUsername(String username);

  User getUserByUsername(String login);


  List<User> findAllUsers();

  User updateUser(User user);


}
