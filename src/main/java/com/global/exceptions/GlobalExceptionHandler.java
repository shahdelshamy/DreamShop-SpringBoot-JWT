package com.global.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String>handlerAccessDeniedException(AccessDeniedException ex){
		String message="You Don't have a permission to do this";
		return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
	}
}
