package com.course.service;

import java.util.List;

import com.course.dto.CourseDto;


public interface CourseService {

	public CourseDto save(CourseDto CourseDto);

	public CourseDto getCourseById(int id);

	public CourseDto updateCourse(CourseDto CourseDto, int id);

	public void deleteCourseById(int id);

	public List<CourseDto> getAllCourses();
}
