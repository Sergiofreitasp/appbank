package com.appbank.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.appbank.controller.rest.exception.StandardError;

@ControllerAdvice
public class BankAppExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestemp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("resouce not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<StandardError> validationEX(CustomException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestemp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("validation problem");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
