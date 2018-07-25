package rwilk.logindemo2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import rwilk.logindemo2.service.IUserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final IUserService IUserService;

  @Autowired
  public SecurityConfig(IUserService IUserService) {
    this.IUserService = IUserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests()
        //.antMatchers(HttpMethod.POST, "/sign_up").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers("/*floor1/**").hasRole("USER")
        .antMatchers("/*floor2/**").hasRole("ADMIN")
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), IUserService));
    //.httpBasic();
  }
}
