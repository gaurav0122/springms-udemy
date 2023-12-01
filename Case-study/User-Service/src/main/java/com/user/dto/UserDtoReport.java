package com.user.dto;

import com.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoReport {
	
	private int userId;
	
	private String name;
	
	private String emailId;
	
	private UserRole role;
	
	private int tasks;
	
	private int hours;
	
}