package com.user.mapper;

import com.user.dto.PostUserDto;
import com.user.dto.UserDto;
import com.user.dto.UserDtoReport;
import com.user.entity.User;

public class UserMapper {
	
//	public static User userDtoToUser(UserDto userDto) {
//		return new User(userDto.getUserId(), userDto.getName(), userDto.getEmailId(), userDto.getRole());
//	}
	
	public static User PostUserDtoToUser(PostUserDto userDto) {
		return new User(userDto.getUserId(), userDto.getName(), userDto.getEmailId(), userDto.getRole(),userDto.getPassword());
	}
	
	public static UserDto userToUserDto(User user) {
		return new UserDto(user.getUserId(), user.getName(), user.getEmailId(), user.getRole());
	}
	
	public static UserDtoReport userDtoToUserDtoReport(UserDto user) {
		return new UserDtoReport(user.getUserId(), user.getName(), user.getEmailId(), user.getRole(),0,0);
	}
}
