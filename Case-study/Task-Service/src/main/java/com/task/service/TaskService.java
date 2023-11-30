package com.task.service;

import java.util.List;

import com.task.dto.TaskDto;

public interface TaskService {
	
	TaskDto addTask(TaskDto taskDto);
	
	List<TaskDto> getTaskListByUserId(int userId);
	
	
}
