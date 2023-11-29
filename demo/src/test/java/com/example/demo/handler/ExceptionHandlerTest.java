package com.example.demo.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.demo.exception.NoSuchUserException;

class ExceptionHandlerTest {

	
	private ExceptionHandler exceptionHandler = new ExceptionHandler(); 
	
	@Test
	void testNoSuchUserExceptionHandler() {
		NoSuchUserException ex = new NoSuchUserException("No Such User");
		
		assertEquals("No Such User", exceptionHandler.handleNoSuchUserException(ex).getBody());
	}

}
