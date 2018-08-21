package rwilk.logindemo2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.model.JWTUser;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.repository.UserRepository;
import rwilk.logindemo2.rest.exception.DifferentPasswordAndConfirmPasswordException;
import rwilk.logindemo2.service.IUserService;

@Service
public class UserService implements IUserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(User user) {
    if (user.getPassword().equals(user.getConfirmPassword())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    }
    throw new DifferentPasswordAndConfirmPasswordException("Password and confirm password must be the same!");
  }

  //Login functionality
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    JWTUser jwtUser = loadApplicationUserByUsername(username);
    return new org.springframework.security.core.userdetails.User(jwtUser.getUsername(), jwtUser.getPassword(),
        AuthorityUtils.createAuthorityList("ROLE_USER"));
  }

  @Override
  public JWTUser loadApplicationUserByUsername(String username) {
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isPresent()) {
      return new JWTUser(user.get().getUsername(), /*"{passwordEncoder}"*/ user.get().getPassword());
    }
    throw new UsernameNotFoundException("Username: " + username + " not found");
  }

  //Other methods
  @Override
  public User getUserByUsername(String username) {
    Optional<User> user = userRepository.findUserByUsername(username);
    user.ifPresent(user1 -> user1.setPassword(""));
    return user.get();
  }

  @Override
  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @Override
  public User updateUser(User user) {
    Long userId = this.getUserByUsername(user.getUsername()).getUserId();
    user.setUserId(userId);
    return userRepository.save(user);
  }
}
