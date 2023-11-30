package com.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int userId;
	
	private String name;
	
	private String emailId;
	
	private UserRole role;
}
