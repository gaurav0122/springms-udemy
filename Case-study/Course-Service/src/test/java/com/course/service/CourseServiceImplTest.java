package com.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.course.dto.CourseDto;
import com.course.entity.Course;
import com.course.mapper.CourseMapper;
import com.course.repository.CourseRespository;

@SpringBootTest
public class CourseServiceImplTest {

    @Mock
    private CourseRespository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService ;

    @Test
    public void testSaveCourse() {
        
        CourseDto inputCourseDto = new CourseDto(1, "coursename", LocalTime.now());

        
//        Course inputCourse = CourseMapper.CourseDtoToCourse(inputCourseDto);
        Course savedCourse =new Course(1, "coursename", LocalTime.now());
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(savedCourse);

        CourseDto result = courseService.save(inputCourseDto);

        assertEquals(savedCourse.getCoursename(), result.getCoursename(), "not done"); 
    }

    @Test
    public void testGetCourseById() {
        int courseId = 1;
        Course course = new Course(1, "coursename", LocalTime.now());
        CourseDto expectedCourseDto = new CourseDto(1, "coursename", LocalTime.now());
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        CourseDto result = courseService.getCourseById(courseId);

         assertEquals(course.getCoursename(),result.getCoursename(),"wrong");
    }

//    @Test
//    public void testUpdateCourse() {
//        int courseId = 1;
//        CourseDto updatedCourseDto = new CourseDto(1, "coursename", LocalTime.now());
//        Course existingCourse = 
//        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
//        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(existingCourse);
//
//        CourseDto result = courseService.updateCourse(updatedCourseDto, courseId);
//
//        // Perform assertions on the result
//        // Assert.assertEquals(expectedResult, result);
//    }

    @Test
    public void testDeleteCourseById() {
        int courseId = 1;
        Course existingCourse =  new Course(1, "coursename", LocalTime.now());
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));

        courseService.deleteCourseById(courseId);

        // Verify that deleteById method is called once
        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(courseId);
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courseList = Arrays.asList(new Course(1, "coursename", LocalTime.now()));
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);

        List<CourseDto> result = courseService.getAllCourses();

         assertEquals(courseList.size(), result.size());
    }
}
