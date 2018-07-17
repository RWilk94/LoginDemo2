package rwilk.logindemo2.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import rwilk.logindemo2.model.ApplicationUser;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser applicationUser = loadApplicationUserByUsername(username);
    return new User(applicationUser.getUsername(), applicationUser.getPassword(),
        AuthorityUtils.createAuthorityList("ROLE_USER"));
    //return User.withUsername("batman").password("pass").roles("USER").build();
  }

  public ApplicationUser loadApplicationUserByUsername(String username) {
    if (username.equals("{noop}batman") || username.equals("batman") ) {
      return new ApplicationUser("{noop}batman", "{noop}pass");
    } else {
      throw new RuntimeException("User not found" + username);
    }
  }
}
