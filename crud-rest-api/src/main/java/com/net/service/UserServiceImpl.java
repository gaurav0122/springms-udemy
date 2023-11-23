package com.net.service;

import org.springframework.stereotype.Service;

import com.net.entity.User;
import com.net.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	// As of after spring 2.3 spring automatically inject beans
	// if it has only one parameterized constructor
	// So it dosent need @autowired annotation
	
	private UserRepository userRepository;
	
	@Override
	public User createuser(User user) {
		return userRepository.save(user);
	}

}
