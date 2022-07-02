package com.youthhomelessnessproject.academicsuccess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("user")
			.password(passwordEncoder().encode("valencia"))
			.roles("User");
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login/admin", "/", "/login").permitAll()
			.anyRequest().authenticated();
			
		
	
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return passwordEncoder;
	}

}
