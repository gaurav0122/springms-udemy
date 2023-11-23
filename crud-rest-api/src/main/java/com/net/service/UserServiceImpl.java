package com.net.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepository.findAll();
		return list;
	}

	@Override
	public User updateUser(User user) {
		
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(user);
		
		return updatedUser;
	}

	@Override
	public void deleteuser(Long userId) {
		userRepository.deleteById(userId);
		
	}

}
