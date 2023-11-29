package com.course.mapper;

import com.course.dto.CourseDto;
import com.course.entity.Course;

public class CourseMapper {

	public static Course CourseDtoToCourse(CourseDto CourseDto) {
		return new Course(CourseDto.getCourseId(), CourseDto.getCoursename(), CourseDto.getCoursetime());
	}
	
	public static CourseDto CourseToCourseDto(Course Course) {
		return new CourseDto(Course.getCourseId(), Course.getCoursename(), Course.getCoursetime());
	}
}
