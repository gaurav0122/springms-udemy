package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.mapper.UserMapper;
import com.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService{

	private UserRepository userRepository;
	
	@Override
	public UserDto save(UserDto UserDto) {
		
		User user = UserMapper.userDtoToUser(UserDto);
		User savedUser = userRepository.save(user);
		return UserMapper.userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found by these id"));
		return UserMapper.userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found by these id"));
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list = userRepository.findAll();
		
		List<UserDto> listDto = new ArrayList<>();
		for(User u:list) {
			listDto.add(UserMapper.userToUserDto(u));
		}
		return listDto;
	}
	
}
