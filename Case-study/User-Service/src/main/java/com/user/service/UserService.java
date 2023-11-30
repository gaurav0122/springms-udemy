package com.user.service;

import java.util.List;

import com.user.dto.TaskCourseDto;
import com.user.dto.UserAllTask;
import com.user.dto.UserDto;

public interface UserService {
	
	public UserDto save(UserDto UserDto);
	
	public UserAllTask getAllTask(int userID);

	public UserDto getUserById(int id);

	public UserDto updateUser(UserDto userDto, int id);

	public void deleteUserById(int id);

	public List<UserDto> getAllUser();

	public List<UserAllTask> getAllTaskForAllMentors();
}
