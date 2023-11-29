package com.course.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
	
	private int courseId;
	
	private String coursename;
	
	private LocalTime coursetime;
}
