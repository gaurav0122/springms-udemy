package com.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.CourseDto;
import com.course.entity.Course;
import com.course.mapper.CourseMapper;
import com.course.repository.CourseRespository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
	
	private CourseRespository CourseRepository;
	
	@Override
	public CourseDto save(CourseDto CourseDto) {
		
		Course Course = CourseMapper.CourseDtoToCourse(CourseDto);
		Course savedCourse = CourseRepository.save(Course);
		return CourseMapper.CourseToCourseDto(savedCourse);
	}

	@Override
	public CourseDto getCourseById(int id) {
		Course Course = CourseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found by these id"));
		return CourseMapper.CourseToCourseDto(Course);
	}

	@Override
	public CourseDto updateCourse(CourseDto CourseDto, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourseById(int id) {
		CourseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found by these id"));
		CourseRepository.deleteById(id);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		List<Course> list = CourseRepository.findAll();
		
		List<CourseDto> listDto = new ArrayList<>();
		for(Course u:list) {
			listDto.add(CourseMapper.CourseToCourseDto(u));
		}
		return listDto;
	}

	
}
