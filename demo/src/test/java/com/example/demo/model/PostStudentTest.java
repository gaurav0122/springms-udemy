package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostStudentTest {

	@Test
	void testStudentConstructors() {
		PostStudent postStudent = new PostStudent();
		PostStudent stud = new PostStudent(32,"nihal",21);
		
		assertNotNull(stud);
		assertNotNull(postStudent);
	}
	
	@Test
	void testGetterSetter() {
		PostStudent stud = new PostStudent(32,"nihal",21);
		assertEquals(32, stud.getAge());
		assertEquals("nihal", stud.getName());
		assertEquals(21, stud.getSalary());
		stud.setAge(10);
		stud.setName("gaurav");
		stud.setSalary(1200);
		assertEquals(10, stud.getAge());
		assertEquals("gaurav", stud.getName());
		assertEquals(1200, stud.getSalary());
		
	}
	
	@Test
	void testToString() {
		PostStudent stud = new PostStudent(32,"nihal",21);
		PostStudent stud1 = stud;
		String s = stud.toString();
		assertNotNull(s);
		assertTrue(stud.equals(stud1));
	}

}

