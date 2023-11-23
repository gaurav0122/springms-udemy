package com.net.controller;

import java.util.Arrays;
import java.util.List;

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

import com.net.dto.UserDto;
import com.net.entity.User;
import com.net.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService service;
	
	public UserController(UserService userService) {
		this.service = userService;
	}
	
	
	//build rest api to create user
	@PostMapping
	public ResponseEntity<UserDto> postUser(@RequestBody UserDto user) {
		return new ResponseEntity<UserDto>(service.createuser(user), HttpStatus.CREATED);
	}
	
	//build rest api for get user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
		UserDto user = service.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	//build for get all users 
	@GetMapping
	public ResponseEntity<List<UserDto>> listOfUser() {
		List<UserDto> list =service.getAllUsers();
		return ResponseEntity.ok().body(list);
	}
	
	
	//build update user
	@PutMapping("{id}")
	public ResponseEntity<UserDto> putUpdateStudent(@PathVariable("id") Long id ,@RequestBody UserDto user) {
		user.setId(id);
		UserDto updatedUser = service.updateUser(user);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	//delete user
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
		service.deleteuser(id);
		
		return ResponseEntity.ok("User deleted successfully");
		
	}
	
}
