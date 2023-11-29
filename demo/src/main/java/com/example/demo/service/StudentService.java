package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.model.PostStudent;
import com.example.demo.model.StudentDto;
import com.example.demo.repo.StudentRepository;

public interface StudentService {

	public Student save(Student student);

	public Student getStudetById(int id);

	public Student updateStudent(PostStudent postStudent, int id);

	public String deleteStudentById(int id);

	public List<Student> getAllStudent();

}
