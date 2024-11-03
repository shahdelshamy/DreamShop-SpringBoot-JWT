package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.DTO.UserDto;
import com.global.exceptions.AlreadyExistExeption;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.User;
import com.global.requests.UserRequest;
import com.global.requests.UserUpdateRequest;
import com.global.response.ApiResponse;
import com.global.service.user.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse>addUser(@RequestBody  UserRequest request){
		try {
			User user=userService.createUser(request);
			UserDto userDto=userService.convertToUserDto(user);
			return ResponseEntity.ok(new ApiResponse("User IS Added", userDto));
		} catch (AlreadyExistExeption e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse>updateUser(@RequestBody UserUpdateRequest request,@RequestParam int userId){
		try {
			User user=userService.updateUser(request, userId);
			UserDto userDto=userService.convertToUserDto(user);
			return ResponseEntity.ok(new ApiResponse("User IS Updated", userDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/{userId}/get")
	public ResponseEntity<ApiResponse>getUser(@PathVariable int userId){
		try {
			User user=userService.getUserById(userId);
			UserDto userDto=userService.convertToUserDto(user);
			return ResponseEntity.ok(new ApiResponse("Sucess", userDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable int userId){
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok(new ApiResponse("User Is Deleted", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
		}
	}
	
	
	
	
}
