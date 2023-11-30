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
public class SignInUser {
	
	private String emailId;
	
	private String password;
	
	private UserRole role;
}
