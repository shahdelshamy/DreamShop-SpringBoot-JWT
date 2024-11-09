package com.global.security.user;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.global.models.Role;
import com.global.models.User;

public class ShopUserDetails implements UserDetails{

	private int id;
	private String email;
	private String password;
	
	private Collection<GrantedAuthority>authorities;
	
	public ShopUserDetails(int id, String email, String password, Collection<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	

	         public ShopUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}



	//for get roles of the user
	public ShopUserDetails buliUserDetails(User user) {
		Set<GrantedAuthority>authorities=user.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getName()) )
				.collect(Collectors.toSet());
		System.out.println(authorities);
		return new ShopUserDetails(
				user.getId(),
				user.getEmail(),
				user.getPassword(),
				authorities
				);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
	

}
