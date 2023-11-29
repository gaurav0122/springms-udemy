package com.example.demo.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NoSuchUserExceptionTest {

	@Test
	void test() {
		NoSuchUserException exception = new NoSuchUserException("message");
		assertEquals("message", exception.getMessage());
	}

}
