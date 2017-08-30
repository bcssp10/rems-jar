package com.rems.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
 
	@Value("${security.user.name}")
	private String username;
	
	@Value("${security.user.password}")
	private String password;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser(username).password(password)
          .authorities("ROLE_USER");
    }
 
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
          //.antMatchers(HttpMethod.POST,"/voucher/general/ledger").permitAll() //logout url http://localhost:9090/login?logout
          .anyRequest().authenticated()
          //.and().httpBasic()
          .and().formLogin()
          .and().csrf().disable();
    }
}