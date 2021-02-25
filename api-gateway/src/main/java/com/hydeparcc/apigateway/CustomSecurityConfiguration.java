package com.hydeparcc.apigateway;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
public class CustomSecurityConfiguration {

	@Qualifier("corsWebFilter")
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();

		corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST"));
		corsConfig.addAllowedHeader("*");
		corsConfig.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, @Qualifier("corsWebFilter")CorsConfigurationSource source) {
		http.authorizeExchange()
		.pathMatchers("/product/**").permitAll()
		.pathMatchers("/cart/**").permitAll()
		.anyExchange().authenticated();
		http.cors().configurationSource(source);
		http.csrf().disable();
		return http.build();

	}

}
