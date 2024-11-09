package com.global.security.jwt;



import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.global.security.user.ShopUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {
	
	@Value("${auth.token.jwtSecret}")
	private String jwtSecret;
	
	@Value("${auth.token.expirationTimeMills}")
	private int expirationTime;
	
	
	public String generateToken(Authentication authentication) {
		
		ShopUserDetails userPrinciple=(ShopUserDetails)authentication.getPrincipal();
		
		List<String>role=userPrinciple.getAuthorities().stream().map(roleGtranted->
			roleGtranted.getAuthority()
				).toList();
		
		
		return Jwts.builder()
				.subject(userPrinciple.getEmail())
				.claim("id", userPrinciple.getId())
				.claim("roles", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() * expirationTime))
				.signWith(key(),SignatureAlgorithm.HS256).compact();	
	}

	private Key key() {
					//decode to number of bits
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
	
	public boolean validToken(String toke) {
		
		try {
			Jwts.parser()
			.setSigningKey(key())
			.build()
			.parseClaimsJws(toke);
			return true;
		} catch (ExpiredJwtException|
				UnsupportedJwtException|MalformedJwtException|SecurityException|
				IllegalArgumentException e) {
			throw new JwtException(e.getMessage());
		}
	}

}
