package com.user.controller;

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

import com.user.dto.UserAllTask;
import com.user.dto.UserDto;
import com.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto) {
		
		UserDto saveduser = userService.save(userDto);
		
		return new ResponseEntity<>(saveduser,HttpStatus.OK);
		
	}
	
	@GetMapping("/task/all/{userId}")
	public ResponseEntity<UserAllTask> getAllTasksAssigned(@PathVariable int userId){
		UserAllTask userAllTask = userService.getAllTask(userId);
		return ResponseEntity.ok(userAllTask);
	}
	
	@GetMapping("/task/all")
	public ResponseEntity<List<UserAllTask>> getAllTasksAssignedForAllMentors(){
		List<UserAllTask> userAllTask = userService.getAllTaskForAllMentors();
		return ResponseEntity.ok(userAllTask);
	}
	
	
	@GetMapping("/{id}")
	public  ResponseEntity<UserDto>  getuserById(@PathVariable int id) {
		logger.info("Get user By id method invoked");
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String>  deleteUserById(@PathVariable int id) {
		logger.info("Delete user By id method invoked");
		userService.deleteUserById(id);
		return new ResponseEntity<>("User deleted successfully...",HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public  ResponseEntity<List<UserDto>> getAlluser() {
		logger.info("Get all users method invoked");
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
}
