package com.net.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.net.dto.UserDto;
import com.net.entity.User;
import com.net.mapper.UserMapper;
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
	public UserDto createuser(UserDto userdto) {
		User user = UserMapper.userDtotoUser(userdto);

		User savedUser = userRepository.save(user);
		
		return UserMapper.usertoUserDto(savedUser); 
	}

	@Override
	public UserDto getUserById(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		UserDto userreturn = UserMapper.usertoUserDto(user.get());
		return userreturn;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> list = userRepository.findAll();
		return list.stream().map(UserMapper::usertoUserDto)
					.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		
		User existingUser = userRepository.findById(user.getId()).get();
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		User updatedUser = userRepository.save(existingUser);
		
		return UserMapper.usertoUserDto(updatedUser);
	}

	@Override
	public void deleteuser(Long userId) {
		userRepository.deleteById(userId);
		
	}

}
