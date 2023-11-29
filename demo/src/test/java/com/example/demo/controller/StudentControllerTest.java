package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.regex.Matcher;

import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Student;
import com.example.demo.model.PostStudent;
import com.example.demo.model.StudentDto;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest
class StudentControllerTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private StudentService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void test() {
//		fail("Not yet implemented");
	}
	
	@Test
	void testPostStudent() throws JsonProcessingException, Exception {
		PostStudent dto = new PostStudent(11,"name",1111);
		
		 Student student = new Student();
	        student.setId(11);
	        student.setName("gaurav");
	        student.setAge(11);
	        student.setSalary(1111.0);
		when(service.save(any())).thenReturn(student);
		 mockmvc.perform(MockMvcRequestBuilders.post("/stud")
	        		.content(mapper.writeValueAsString(dto))
	                .contentType(MediaType.APPLICATION_JSON))
//		 			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	                
//	                .andExpect(jsonPath("$",student));
		
	}


	@Test
	void testGetAll() throws Exception {
	     Student student = new Student();
	        student.setId(11);
	        student.setName("gaurav");
	        student.setAge(11);
	        student.setSalary(1111.0);
	        when(service.getAllStudent()).thenReturn(Arrays.asList(student));
	        mockmvc.perform(MockMvcRequestBuilders.get("/stud/all")
	                .contentType(MediaType.APPLICATION_JSON))
	        		.andExpect(jsonPath("$").isArray())
	                .andExpect(status().isOk());
	                
	}
}