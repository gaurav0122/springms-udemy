package com.net.javaguides.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.net.javaguides.bean.Student;


@RestController
public class StudentController {
	
	
	@GetMapping("/student")
	public Student student() {
		return new Student(12, "name", "last");
	}

	@GetMapping("/students")
	public List<Student> listOfstudent() {
		return Arrays.asList(new Student(12, "name", "last"), new Student(14, "name2", "last2"));
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
	
	//@postmapping and @requestbody
	@PostMapping("/student")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student postStudent(@RequestBody Student student) {
		System.out.println(student.toString());
		student.setId((int)(Math.random()*100));
		return student;
	}
	
	//@putmapping and @requestbody
	@PutMapping("/student/{id}/update")
	public Student putUpdateStudent(@RequestBody Student student,@PathVariable("id") int sId) {
		System.out.println(student.toString());
		
		return student;
	}
	
	//@deletemapping 
	@DeleteMapping("/student/{id}/delete")
	public String dleteStudent(@PathVariable("id") int studentId) {
		System.out.println(studentId);
		
		return "student deleted successfully" + studentId;
	}
	
}
