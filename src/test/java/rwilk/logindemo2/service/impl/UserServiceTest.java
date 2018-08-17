package rwilk.logindemo2.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import rwilk.logindemo2.model.JWTUser;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.repository.UserRepository;
import rwilk.logindemo2.rest.exception.DifferentPasswordAndConfirmPasswordException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  private User user;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    //when(userRepository.save(any(User.class))).thenReturn(any(User.class));
    user = new User(null, "username", "email@email.com", "password", "password", null, null, null);
    when(userRepository.findUserByUsername("username")).thenReturn(user);
  }

  @Test
  public void registerUserTest() {
    User user = new User(null, "Username", "email@email.com", "password", "password", null, null, null);
    userService.registerUser(user);
    verify(userRepository, times(1)).save(any());
  }

  @Test(expected = DifferentPasswordAndConfirmPasswordException.class)
  public void shouldThrowDifferentPasswordAndConfirmPasswordException() {
    User user = new User(null, "Username", "email@email.com", "passwo", "password", null, null, null);
    userService.registerUser(user);
  }

  @Test
  public void shouldReturnUserWhenFindUserByUsername() {
    userService.getUserByUsername("username");
    verify(userRepository, times(1)).findUserByUsername(any());
  }

  @Test
  public void findAllUserTest() {
    userService.findAllUsers();
    verify(userRepository, times(1)).findAll();
  }

  @Test
  public void updateUserTest() {
    user.setEmail("email@wolf.pl");
    userService.updateUser(user);
    verify(userRepository, times(1)).save(any());
  }

  @Test
  public void shouldReturnUserWhenLoadApplicationUserByUsername() {
    User user = userService.getUserByUsername("username");
    JWTUser jwtUser = userService.loadApplicationUserByUsername(user.getUsername());
    assertEquals(user.getUsername(), jwtUser.getUsername());
    assertEquals(user.getPassword(), jwtUser.getPassword());
  }

  @Test(expected = UsernameNotFoundException.class)
  public void shouldThrowUsernameNotFoundException() {
    User user = userService.getUserByUsername("username");
    user.setUsername("otherUsername");

    userService.loadApplicationUserByUsername(user.getUsername());
  }

  @Test
  public void shouldReturnUserDetailsWhenLoadUserByUsername() {
    User user = userService.getUserByUsername("username");
    UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
    assertEquals(userDetails.getUsername(), user.getUsername());
  }
}
