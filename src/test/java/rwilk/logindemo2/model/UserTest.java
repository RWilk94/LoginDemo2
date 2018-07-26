package rwilk.logindemo2.model;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

  private User user;

  @Before
  public void setUp() {
    user = new User();
  }

  @Test
  public void setEmailTest() {
    user.setEmail("email");
    assertEquals("email", user.getEmail());
  }

  @Test
  public void setOldPasswordTest() {
    user.setOldPassword("oldPassword");
    assertEquals("oldPassword", user.getOldPassword());
  }

  @Test
  public void setCreatedDateTest() {
    user.setCreated(new Date());
    assertEquals(new Date(), user.getCreated());
  }

}
