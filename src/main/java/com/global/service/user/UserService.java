package com.global.service.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.DTO.UserDto;
import com.global.exceptions.AlreadyExistExeption;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.User;
import com.global.repositories.UserRepository;
import com.global.requests.UserRequest;
import com.global.requests.UserUpdateRequest;

import ch.qos.logback.core.model.Model;

@Service
public class UserService implements UserInterface{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper=new ModelMapper();

	
	@Override
	public User getUserById(int userId) {
		User user=userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("The User Not Found")
		);
		return user;
		
	}

	@Override
	public User createUser(UserRequest request) {
	 Optional<User> userOptional=userRepository.findByEmail(request.getEmail());
		if(userOptional.isEmpty()) {
			User user=new User();
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			
			return userRepository.save(user);
		}else {
			throw new AlreadyExistExeption("This User Already Exist");
		}
	}

	@Override
	public User updateUser(UserUpdateRequest request, int userId) {
		User user=userRepository.findById(userId).orElseThrow(()->
			new ResourceNotFoundException("The User Not Found")
				);
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user=this.getUserById(id);
		userRepository.deleteById(id);
	}

	
	public UserDto convertToUserDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	
}
