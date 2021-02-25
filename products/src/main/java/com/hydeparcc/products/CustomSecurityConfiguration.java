package com.hydeparcc.products;

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
        .antMatchers(HttpMethod.GET, "/product/**").permitAll()
        .antMatchers(HttpMethod.POST, "/product/**").permitAll()
        .anyRequest().authenticated().and().csrf().disable().cors().disable();
	}
}
