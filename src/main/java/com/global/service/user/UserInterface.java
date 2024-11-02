package com.global.service.user;

import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import com.global.DTO.UserDto;
import com.global.models.User;
import com.global.requests.UserRequest;
import com.global.requests.UserUpdateRequest;

public interface UserInterface {
	
	UserDto getUserById(int userId);
	
	UserDto createUser(UserRequest request);
	UserDto updateUser(UserUpdateRequest request,int userId);
	void deleteUser(int id);

}
