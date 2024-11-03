package com.global.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;

import com.global.exceptions.AlreadyExistExeption;
import com.global.requests.UserRequest;
import com.global.service.user.UserService;

@Configuration
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		createDefaultUserIfNotExist();
		
	}

	private void createDefaultUserIfNotExist() {
		for (int i=1;i<=5;i++) {
			try {
				UserRequest user=new UserRequest();
				user.setEmail("user"+i+"@gmail.com");
				user.setPassword("123456");;
				user.setFirstName("User");
				user.setLastName("User"+i);
				userService.createUser(user);
			} catch (AlreadyExistExeption e) {
				continue;
			}
		}
		
	}
	
	

}
