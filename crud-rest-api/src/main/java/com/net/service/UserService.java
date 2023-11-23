package com.net.service;

import java.util.List;

import com.net.entity.User;

public interface UserService {

	User createuser(User user);
	
	User getUserById(Long id);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteuser(Long userId);
}
