package com.user.dto;

import java.util.List;

import com.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAllTask {

	private int userId;

	private String name;

	private String emailId;

	private UserRole role;
	
	private List<TaskCourseDto> tasks;
}
