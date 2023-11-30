package com.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.task.dto.CourseDto;
import com.task.dto.TaskDto;
import com.task.dto.UserDto;
import com.task.dto.UserRole;
import com.task.entity.Task;
import com.task.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	private WebClient webClient;

	private ModelMapper modelMapper;

	@Override
	public TaskDto addTask(TaskDto taskDto) {
		UserDto user = getUserByWEbCient(taskDto.getUserId());
		if (user.getRole().equals(UserRole.ADMIN)) {
			throw new RuntimeException("Admin cannnot be enrroled to course");
		}
		getCourseByWEbCient(taskDto.getCourseId());
		Task task = modelMapper.map(taskDto, Task.class);
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getTaskListByUserId(int userId) {
		
		List<Task> listTask = taskRepository.findByUserId(userId);
		
		return listTask.stream().map((task)->modelMapper.map(task, TaskDto.class))
				.collect(Collectors.toList());
		
	}
	
	
	private UserDto getUserByWEbCient(int userId) {

		UserDto dto = webClient.get()
				.uri("http://localhost:8081/api/user/" + userId)
				.retrieve()
				.bodyToMono(UserDto.class).block();

		return dto;

	}

	private CourseDto getCourseByWEbCient(int courseId) {

		CourseDto dto = webClient.get()
				.uri("http://localhost:8082/api/course/" + courseId)
				.retrieve()
				.bodyToMono(CourseDto.class).block();

		return dto;

	}

	

}
