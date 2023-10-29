package com.app.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/accounts/user/**")
			.authorizeRequests().anyRequest().hasAuthority("USER")
			.and()
			.formLogin()
				.loginPage("/accounts/user/login")
				.usernameParameter("username")
				.loginProcessingUrl("/accounts/user/login")
				.defaultSuccessUrl("/accounts/user/profile")
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/accounts/user/logout")
				.logoutSuccessUrl("/");
	}

}
