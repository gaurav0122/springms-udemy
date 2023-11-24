package com.net.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
		 ResourceNotFoundException exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("USER_NOT_FOUND");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_EXTENDED);
		
	}
	
	@ExceptionHandler(value = EmailNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleEmailNotFoundException(
		 EmailNotFoundException exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("USER_WITH_THE_EMAIL_ALREADY_PRESENT");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(
		 Exception exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("INTERNAL_SERVER_ERROR");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
