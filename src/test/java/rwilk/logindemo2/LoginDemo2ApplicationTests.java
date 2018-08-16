package rwilk.logindemo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginDemo2ApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Test
  public void applicationContextTest() {
    LoginDemo2Application.main(new String[]{});
  }

}
