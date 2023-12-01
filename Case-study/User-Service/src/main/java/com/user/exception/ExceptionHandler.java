package com.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundEx(UserNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler(AdminGetTaskException.class)
	public ResponseEntity<String> handleUserNotFoundEx(AdminGetTaskException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> map = new HashMap<>();
		 	
		ex.getAllErrors().forEach((error)->{
			String feildName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(feildName,message);
		});
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
}
