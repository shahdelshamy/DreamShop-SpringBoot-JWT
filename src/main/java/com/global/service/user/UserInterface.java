package com.global.service.user;

import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import com.global.DTO.UserDto;
import com.global.models.User;
import com.global.requests.UserRequest;
import com.global.requests.UserUpdateRequest;

public interface UserInterface {
	
	User getUserById(int userId);
	
	User createUser(UserRequest request);
	User updateUser(UserUpdateRequest request,int userId);
	void deleteUser(int id);

}
