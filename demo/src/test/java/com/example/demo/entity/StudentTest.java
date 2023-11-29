package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentTest {

	
	@Test
	void test() {
		Student student = new Student();
		Student student1 = new Student(12,12,"abc",21d);
		Student student3 = Student.builder()
								.id(12)
								.age(12)
								.name("abc")
								.salary(21d)
								.build();
		student.getAge();
		student.getName();
		student.getSalary();
		student.getId();
		student.setId(12);
		student.setSalary(21d);
		student.setName("abc");
		student.setAge(12);
		student.toString();
		assertNotEquals(student, student1);
		assertEquals(student3.getId(), student1.getId());
	}
	
	

}
