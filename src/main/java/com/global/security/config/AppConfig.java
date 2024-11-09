package com.global.security.config;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.global.security.jwt.AuthTokenFilter;
import com.global.security.jwt.JwtAuthEntryPoint;
import com.global.security.user.ShopUserDetailsService;

import jakarta.websocket.Session;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class AppConfig {
	
	@Autowired
	private ShopUserDetailsService shopUserDetailsService;
	
	@Autowired
	private JwtAuthEntryPoint entryPoint;
	
	List<String>SECURED_URL=List.of("/api/cartItem/**","/api/cart/**");
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager(); 
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider provider=  new DaoAuthenticationProvider();
		  provider.setUserDetailsService(shopUserDetailsService);
		  provider.setPasswordEncoder(passwordEncoder());
		  return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		
		http.csrf().disable()
		.exceptionHandling(ex->ex.authenticationEntryPoint(entryPoint))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth->
			auth.requestMatchers("/api/cartItem/**").authenticated()
			//auth.requestMatchers("/api/product**").hasRole("ADMIN")
				.anyRequest().permitAll()
				);
		
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
