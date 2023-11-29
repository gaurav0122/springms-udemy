package com.example.demo.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Student;
import com.example.demo.model.PostStudent;
import com.example.demo.repo.StudentRepository;


@SpringBootTest
class StudentServiceImplTest {

	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentServiceImpl serviceImpl;
	
	Student student;
	
	@BeforeEach
	public void beforeEach() {
		student = Student.builder()
							.name("gaurav")
							.age(12)
							.salary(12000)
							.id(2).build();
	}
	
	@Test
	void testgetStudetById() {
		when(repository.findById(2)).thenReturn(Optional.of(student));
		assertEquals(serviceImpl.getStudetById(2), student);
	}
	
	@Test
	void testSaveStudent() {
		when(repository.save(student)).thenReturn(student);
		assertEquals(serviceImpl.save(student), student);
	}
	
	@Test
	void testDeleteStudent() {
//		when(repository.deleteById(2)).thenReturn("");
		when(repository.findById(2)).thenReturn(Optional.of(student));
		assertEquals(serviceImpl.deleteStudentById(2), "Student deleted Succesfully");
	}
	
	@Test
	void testGetAllStudentMethod() {
		List<Student> list = new ArrayList<>();
		list.add(student);
		when(repository.findAll()).thenReturn(list);
		
		assertEquals(serviceImpl.getAllStudent(), list);
	}
	
	@Test
	void updateStudentMethodTest() {
		PostStudent stud = new PostStudent(32,"nihal",21);
	
		when(repository.findById(2)).thenReturn(Optional.of(student));
		
		when(repository.save(any())).thenReturn(student);
		
		assertEquals(serviceImpl.updateStudent(stud, 2), student);
	}

}
