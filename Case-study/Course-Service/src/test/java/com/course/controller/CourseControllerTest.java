package com.course.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.course.dto.CourseDto;
import com.course.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostCourse() throws Exception {
    	
    	 CourseDto courseDto = new CourseDto(1,"coursename",LocalTime.now());
         CourseDto savedCourse = courseDto;
    	
        when(courseService.save(any(CourseDto.class))).thenReturn(savedCourse);

        mockMvc.perform(post("/api/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(courseDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCourseById() throws Exception {
        int courseId = 1;
        CourseDto courseDto = new CourseDto(1,"coursename",LocalTime.now());

        when(courseService.getCourseById(courseId)).thenReturn(courseDto);

        mockMvc.perform(get("/api/course/{id}", courseId))
                .andExpect(status().isOk());
                
    }

    @Test
    public void testDeleteCourseById() throws Exception {
        int courseId =1;
        
        mockMvc.perform(delete("/api/course/{id}", courseId))
                .andExpect(status().isOk());
        
        verify(courseService, times(1)).deleteCourseById(courseId);
    }

    @Test
    public void testGetAllCourses() throws Exception {
        CourseDto courseDto = new CourseDto(1,"coursename",LocalTime.now());
        
        List<CourseDto> courseList = Arrays.asList(courseDto);
        
        when(courseService.getAllCourses()).thenReturn(courseList);

        mockMvc.perform(get("/api/course/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(courseList.size()));
    }
}

