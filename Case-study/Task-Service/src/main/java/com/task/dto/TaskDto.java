package com.task.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

	private int id;
	
	private int userId;
	
	private int courseId;
	
	private LocalDate startdate;
	
	private LocalDate endDate;
	
	private LocalTime hoursADay;
	
}
