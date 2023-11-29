package com.user.mapper;

import com.user.dto.UserDto;
import com.user.entity.User;

public class UserMapper {
	
	public static User userDtoToUser(UserDto userDto) {
		return new User(userDto.getUserId(), userDto.getName(), userDto.getEmailId(), userDto.getRole());
	}
	
	public static UserDto userToUserDto(User user) {
		return new UserDto(user.getUserId(), user.getName(), user.getEmailId(), user.getRole());
	}
}
