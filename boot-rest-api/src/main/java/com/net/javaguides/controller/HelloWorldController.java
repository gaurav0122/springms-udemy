package com.net.javaguides.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.net.javaguides.bean.Student;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/student")
	public Student student() {
		return new Student(12, "name", "last");
	}

	@GetMapping("/students")
	public List<Student> listOfstudent() {
		return Arrays.asList(new Student(12, "name", "last"), new Student(12, "name2", "last2"));
	}

	// @pathvariable
	@GetMapping("/student/{id}/{first-name}/{last-name}")
	public Student studentByPathVariable(@PathVariable("id") int id, 
			@PathVariable("first-name") String firstname,
			@PathVariable("last-name") String lastName) {
		
		Student s = new Student(id, firstname, lastName);
		return s;
	}

	// @pathvariable
	@GetMapping("/student/query")
	public Student studentByRequestparam(@RequestParam("id") int id, 
			@RequestParam(name = "firstName",defaultValue = "default") String firstname,
			@RequestParam("lastName") String lastName) {
		
		Student s = new Student(id, firstname, lastName);
		return s;
	}
}
