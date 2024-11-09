package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.requests.LoginRequest;
import com.global.response.ApiResponse;
import com.global.response.LoginResponse;
import com.global.security.jwt.JwtUtils;
import com.global.security.user.ShopUserDetails;

@RestController()
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse>login(@RequestBody  LoginRequest request){
		try {
			Authentication authentication=authenticationManager
					        .authenticate(new UsernamePasswordAuthenticationToken(
					        		request.getEmail(), request.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token=jwtUtils.generateToken(authentication);
			ShopUserDetails userDetails=(ShopUserDetails)authentication.getPrincipal();
			LoginResponse response=new LoginResponse(userDetails.getId(),token);
			return ResponseEntity.ok(new ApiResponse("Login Sucessfull",response));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
}
