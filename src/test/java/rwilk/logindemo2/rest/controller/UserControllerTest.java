package rwilk.logindemo2.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import rwilk.logindemo2.model.User;
import rwilk.logindemo2.rest.exception.RestExceptionHandler;
import rwilk.logindemo2.service.impl.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

  private String userBody = "{\"username\": \"username\"}";
  private String emptyUserBody = "{\"username\": \"\"}";
  private String updateUserBody = "{\t\"username\": \"username\",\n"
      + "\t\"email\": \"email@email.pl\",\n"
      + "\t\"password\": \"\",\n"
      + "\t\"confirmPassword\": \"\",\n"
      + "\t\"oldPassword\": \"\",\n"
      + "\t\"created\": \"\"}";
  private String emptyUpdateUserBody = "{\t\"username\": \"\",\n"
      + "\t\"email\": \"\",\n"
      + "\t\"confirmPassword\": \"\",\n"
      + "\t\"oldPassword\": \"\",\n"
      + "\t\"created\": \"\"}";

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  private MockMvc mockMvc;
  private User user;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new RestExceptionHandler())
        .build();
    user = new User(null, "username", "email@email.com", "password", "password", null, null);
  }

  @Test
  public void shouldRegisterUser() {
    userController.registerUser(user);
    verify(userService, times(1)).registerUser(user);
  }

  @Test
  public void shouldReturnUserWhenUsernameIsFilled() throws Exception {
    mockMvc.perform(
        post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userBody))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldThrowUserNotFoundExceptionWhenUsernameIsEmpty() throws Exception {
    mockMvc.perform(
        post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(emptyUserBody))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldUpdateUserWhenUserIsFilled() throws Exception {
    mockMvc.perform(
        put("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(updateUserBody))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldThrowUserNotFoundExceptionWhenUserIsEmpty() throws Exception {

    mockMvc.perform(
        put("/user")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

   /* // given
    UserNotFoundException exception = new UserNotFoundException(HttpStatus.NOT_FOUND, "");

    // when
    when(userService.updateUser(any())).thenThrow(exception);

    MockHttpServletResponse result = mockMvc.perform(
        put("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isNotFound()).andReturn().getResponse();

    // then
    Assert.assertEquals(HttpStatus.NOT_FOUND.toString(), String.valueOf(result.getStatus()));*/
  //}

}
