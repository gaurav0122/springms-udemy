package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentDtoTest {

	@Test
	void testStudentConstructors() {
		StudentDto StudentDto = new StudentDto();
		StudentDto stud = new StudentDto(1,32,"nihal",21);
		
		assertNotNull(stud);
		assertNotNull(StudentDto);
	}
	
	@Test
	void testGetterSetter() {
		StudentDto stud = new StudentDto(1,32,"nihal",21);
		assertEquals(1, stud.getId());
		assertEquals(32, stud.getAge());
		assertEquals("nihal", stud.getName());
		assertEquals(21, stud.getSalary());
		stud.setAge(10);
		stud.setName("gaurav");
		stud.setSalary(1200);
		stud.setId(3);
		assertEquals(3, stud.getId());
		assertEquals(10, stud.getAge());
		assertEquals("gaurav", stud.getName());
		assertEquals(1200, stud.getSalary());
		
	}
	
	@Test
	void testToString() {
		StudentDto stud = new StudentDto(1,32,"nihal",21);
		StudentDto stud1 = stud;
		String s = stud.toString();
		assertNotNull(s);
		assertTrue(stud.equals(stud1));
	}
	

	@Test
	void testBuilder() {
		StudentDto stud = StudentDto.builder()
								.id(1)
								.name("gaur")
								.salary(1200)
								.age(21)
								.build();
		assertNotNull(stud);
		assertNotNull(StudentDto.builder().age(21).toString());
	}


}
