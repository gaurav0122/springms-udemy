package com.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.TaskDto;
import com.task.service.TaskService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;
	
	
	@PostMapping("/{userId}/{courseId}")
	public ResponseEntity<TaskDto> postTask(@RequestBody TaskDto taskDto,
				@PathVariable int userId,
				@PathVariable int courseId) {
		
		taskDto.setUserId(userId);
		taskDto.setCourseId(courseId);
		TaskDto savedTask = taskService.addTask(taskDto);
		
		return new ResponseEntity<>(savedTask,HttpStatus.OK);
		
	}
	
	@GetMapping("/all/{userId}")
public ResponseEntity<List<TaskDto>> getAllTaskByUserId(@PathVariable int userId) {
		
		List<TaskDto> savedTask = taskService.getTaskListByUserId(userId);
		
		return new ResponseEntity<>(savedTask,HttpStatus.OK);
		
	}
}
