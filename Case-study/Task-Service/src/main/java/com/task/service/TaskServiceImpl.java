package com.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.task.dto.CourseDto;
import com.task.dto.TaskCourseDto;
import com.task.dto.TaskDto;
import com.task.dto.UserDto;
import com.task.dto.UserRole;
import com.task.entity.Task;
import com.task.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	private TaskRepository taskRepository;

	private WebClient webClient;

	private ModelMapper modelMapper;
	
	private RabbitTemplate rabbitTemplate;

	public TaskServiceImpl(TaskRepository taskRepository, WebClient webClient, ModelMapper modelMapper,
			RabbitTemplate rabbitTemplate) {
		super();
		this.taskRepository = taskRepository;
		this.webClient = webClient;
		this.modelMapper = modelMapper;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public TaskDto addTask(TaskDto taskDto) {
		UserDto user = getUserByWEbCient(taskDto.getUserId());
		if (user.getRole().equals(UserRole.ADMIN)) {
			throw new RuntimeException("Admin cannnot be enrolled to course");
		}
		CourseDto dto = getCourseByWEbCient(taskDto.getCourseId());
		Task task = modelMapper.map(taskDto, Task.class);
		Task savedTask = taskRepository.save(task);
		rabbitTemplate.convertAndSend(exchange, routingKey, String .format("Task added for %s user with course name %s", user.getName(), dto.getCoursename()));
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getTaskListByUserIdReport(int userId) {
		List<Task> listTask = taskRepository.findByUserId(userId);
		return listTask.stream().map( (m)->modelMapper.map(m, TaskDto.class)).collect(Collectors.toList());
		
	}

	
	
	@Override
	public List<TaskCourseDto> getTaskListByUserId(int userId) {
		
		List<Task> listTask = taskRepository.findByUserId(userId);
		
		return listTask.stream().map((task)->
				new TaskCourseDto(task.getId(), userId,getCourseByWEbCient(task.getCourseId()) ,
						task.getStartdate(), task.getEndDate(), task.getHoursADay()) 
				
				)
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
		CourseDto dto;
		try {
			dto = webClient.get()
					.uri("http://localhost:8082/api/course/" + courseId)
					.retrieve()
					.bodyToMono(CourseDto.class).block();
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		

		return dto;

	}

	

}
