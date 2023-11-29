package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.model.PostStudent;
import com.example.demo.model.StudentDto;
import com.example.demo.service.StudentService;
import com.example.demo.serviceimpl.StudentServiceImpl;


@RestController
@RequestMapping("/stud")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<?> postStudent(@RequestBody PostStudent postStudent) {
		logger.info("Post student method invoked");
		Student student = Student.builder()
							.age(postStudent.getAge())
							.salary(postStudent.getSalary())
							.name(postStudent.getName())
							.build();
		
		return new ResponseEntity<>(convertToDto(studentService.save(student)),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<?>  getStudetById(@PathVariable int id) {
		logger.info("Get student By id method invoked");
		return new ResponseEntity<>(convertToDto(studentService.getStudetById(id)),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public  ResponseEntity<?>  updateStudent(@RequestBody PostStudent postStudent , @PathVariable int id) {
		logger.info("Update student By id method invoked");
		return new ResponseEntity<>(convertToDto(studentService.updateStudent(postStudent,id)),HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public  ResponseEntity<?>  deleteStudetById(@PathVariable int id) {
		logger.info("Delete student By id method invoked");
		return new ResponseEntity<>(studentService.deleteStudentById(id),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public  ResponseEntity<?> getAllStudent() {
		logger.info("Get all students method invoked");
		return new ResponseEntity<>(studentService.getAllStudent(),HttpStatus.OK);
	}
	

	public StudentDto convertToDto(Student save) {
		return StudentDto.builder()
					.id(save.getId())
					.name(save.getName())
					.salary(save.getSalary())
					.age(save.getAge())
					.build();
	}
}
