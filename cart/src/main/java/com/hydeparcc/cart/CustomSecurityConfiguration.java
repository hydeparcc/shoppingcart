package com.hydeparcc.cart;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/cart/**").permitAll()
        .antMatchers(HttpMethod.POST, "/cart/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .cors().disable();
	}
}
