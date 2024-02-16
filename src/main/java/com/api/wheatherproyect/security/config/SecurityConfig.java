package com.api.wheatherproyect.security.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.wheatherproyect.security.jwt.JwtAuthenticationFilter;
import com.google.common.util.concurrent.RateLimiter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
	private static final RateLimiter rateLimiter = RateLimiter.create(3.6, 1, TimeUnit.SECONDS);
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf ->
					csrf
					.disable()
				)
				.authorizeHttpRequests(authRequest -> 
					authRequest
						.requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(sessionManager -> 
						sessionManager
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	@Bean
	public RateLimiter ratelimiter() {
		return rateLimiter;
		
	}
	
	
	
}
