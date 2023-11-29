package com.course.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.CourseDto;
import com.course.service.CourseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<CourseDto> postCourse(@RequestBody CourseDto CourseDto) {
		
		CourseDto savedCourse = courseService.save(CourseDto);
		
		return new ResponseEntity<>(savedCourse,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<CourseDto>  getCourseById(@PathVariable int id) {
		logger.info("Get Course By id method invoked");
		return new ResponseEntity<>(courseService.getCourseById(id),HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String>  deleteCourseById(@PathVariable int id) {
		logger.info("Delete Course By id method invoked");
		courseService.deleteCourseById(id);
		return new ResponseEntity<>("Course deleted successfully...",HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public  ResponseEntity<List<CourseDto>> getAllCourse() {
		logger.info("Get all Courses method invoked");
		return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
	}
}
