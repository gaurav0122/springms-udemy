package com.task.service;

import java.util.List;

import com.task.dto.TaskCourseDto;
import com.task.dto.TaskDto;

public interface TaskService {
	
	TaskDto addTask(TaskDto taskDto);
	
	List<TaskCourseDto> getTaskListByUserId(int userId);

	List<TaskDto> getTaskListByUserIdReport(int userId);
	
	
}
