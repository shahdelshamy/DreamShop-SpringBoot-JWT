package com.global.security.jwt;

import java.io.IOException;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import com.global.security.user.ShopUserDetailsService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ShopUserDetailsService shopUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response,
									FilterChain filterChain)
			throws ServletException, IOException {
		 String token;
		String header=request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			  token= header.substring(7);
			try {
				if(!token.isEmpty() && jwtUtils.validToken(token)) {
					String email= jwtUtils.getUserNameFromToken(token);
					System.out.println("555"+email);
					UserDetails userDetails= shopUserDetailsService.loadUserByUsername(email);
					System.out.println("555"+userDetails);
					     //To Save the data in security context holder
					Authentication auth=  new UsernamePasswordAuthenticationToken(userDetails,null ,userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
					
				}
			} catch (JwtException e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(" Invalid or expired token,you may login and try again");
			}catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write(e.getMessage());
			}
			
		}
		
		 filterChain.doFilter(request, response);
		
	}
	
	
}
