package com.global.data;

import java.util.Optional;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.global.exceptions.AlreadyExistExeption;
import com.global.models.Role;
import com.global.repositories.RoleRepository;
import com.global.requests.UserRequest;
import com.global.service.user.UserService;

@Configuration
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Set<String>roles=Set.of("ROLE_ADMIN","ROLE_USER");
		createDefaultRolesIfNotExist(roles);
		createDefaultAdminIfNotExist();
		createDefaultUserIfNotExist();
		
	}

	private void createDefaultAdminIfNotExist() {
		for (int i=1;i<=2;i++) {
			try {
				Role role=roleRepository.findByName("ROLE_ADMIN").get();
				UserRequest user=new UserRequest();
				user.setEmail("admin"+i+"@gmail.com");
				user.setPassword("123456");
				user.setFirstName("Admin");
				user.setLastName("Admin"+i);
				user.setRoles(Set.of(role));
				userService.createUser(user);
			} catch (AlreadyExistExeption e) {
				continue;
			}
		}
		
	}
	
	
	private void createDefaultUserIfNotExist() {
		for (int i=1;i<=5;i++) {
			try {
				Role role=roleRepository.findByName("ROLE_USER").get();
				UserRequest user=new UserRequest();
				user.setEmail("user"+i+"@gmail.com");
				user.setPassword("123456");
				user.setFirstName("User");
				user.setLastName("User"+i);
				user.setRoles(Set.of(role));
				userService.createUser(user);
			} catch (AlreadyExistExeption e) {
				continue;
			}
		}
		
	}
	
	private void createDefaultRolesIfNotExist(Set<String>roles) {
		roles.stream().forEach(role->{
			Optional<Role> roleOptional=roleRepository.findByName(role);
			if(roleOptional.isEmpty()){
				Role roleObject=new Role(role);
				roleRepository.save(roleObject);
			}
		}
		
				);
	}
	
	
	

}
