package com.user.dto;

import com.user.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostUserDto {
	
	private int userId;
	
	@NotEmpty(message = "name must not be empty")
	private String name;	
	
	@Email(message = "email is not valid")
	private String emailId;
	
	@NotNull
	private UserRole role;
	
	@NotEmpty
	private String password;
	
}