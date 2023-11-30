package com.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dto.TaskCourseDto;
import com.user.dto.UserAllTask;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.entity.UserRole;
import com.user.exception.UserNotFoundException;
import com.user.mapper.UserMapper;
import com.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService{

	private UserRepository userRepository;
	
	private RestTemplate restTemplate;
	
	@Override
	public UserDto save(UserDto UserDto) {
		
		User user = UserMapper.userDtoToUser(UserDto);
		User savedUser = userRepository.save(user);
		return UserMapper.userToUserDto(savedUser);
	}
	

	@Override
	public UserAllTask getAllTask(int userID) {
		UserDto user = getUserById(userID);
		ResponseEntity<TaskCourseDto[]> taskCourse= restTemplate.getForEntity("http://localhost:8083/api/task/all/"+userID, TaskCourseDto[].class); 
		List<TaskCourseDto> listTaskCourse = Arrays.asList( taskCourse.getBody());
		UserAllTask userAllTask = new UserAllTask(userID, user.getName(), user.getEmailId(), user.getRole(), listTaskCourse);
		return userAllTask;
	}
	
	

	@Override
	public List<UserAllTask> getAllTaskForAllMentors() {
		List<UserDto> users = getAllUser();
		
		return users.stream()
				.filter(u->u.getRole().equals(UserRole.MENTOR))
				.map((u)->getAllTask(u.getUserId()))
				.collect(Collectors.toList());
				
	}


	@Override
	public UserDto getUserById(int id) {
		User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found by these id -> "+id));
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
