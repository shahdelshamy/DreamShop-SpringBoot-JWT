package com.global.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.User;
import com.global.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShopUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(email).orElseThrow(()->
				new ResourceNotFoundException("User Not Found")
				);
		
		ShopUserDetails shopUserDetails=new ShopUserDetails();
		System.out.println("555"+user.getFirstName());
		return shopUserDetails.buliUserDetails(user) ;
	}
	
	

}
