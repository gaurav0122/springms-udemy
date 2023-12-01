package com.user.service;

import java.util.List;

import com.user.dto.PostUserDto;
import com.user.dto.SignInUser;
import com.user.dto.TaskCourseDto;
import com.user.dto.UserAllTask;
import com.user.dto.UserDto;
import com.user.dto.UserDtoReport;

public interface UserService {
	
	public UserDto save(PostUserDto userDto);
	
	public UserAllTask getAllTask(int userID);

	public UserDto getUserById(int id);

	public UserDto updateUser(UserDto userDto, int id);

	public void deleteUserById(int id);

	public List<UserDto> getAllUser();

	public List<UserAllTask> getAllTaskForAllMentors();

	public String signin(SignInUser signInUser);

	public List<UserDto> getAllMentors();

	public List<UserDtoReport> getAllMentorsWithReport();
}
