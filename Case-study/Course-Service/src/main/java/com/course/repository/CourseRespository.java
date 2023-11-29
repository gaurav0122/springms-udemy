package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.entity.Course;

public interface CourseRespository extends JpaRepository<Course, Integer>{
	
}
