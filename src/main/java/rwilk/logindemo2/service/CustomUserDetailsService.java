package rwilk.logindemo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import rwilk.logindemo2.model.JWTUser;
import rwilk.logindemo2.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JWTUser JWTUser = loadApplicationUserByUsername(username);
        return new User(JWTUser.getUsername(), JWTUser.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
        //return User.withUsername("batman").password("pass").roles("USER").build();
    }

    public JWTUser loadApplicationUserByUsername(String username) {

        System.out.println(userRepository.findAll().size());

        rwilk.logindemo2.model.User user = userRepository.findUserByUsername(username);
        if (user != null) {
            return new JWTUser(user.getUsername(), "{noop}" + user.getPassword());
        } else {
            throw new RuntimeException("User not found: " + username);
        }
   /* if (username.equals("{noop}batman") || username.equals("batman") ) {
      return new JWTUser("{noop}batman", "{noop}pass");
    } else {
      throw new RuntimeException("User not found" + username);
    }*/
    }
}
