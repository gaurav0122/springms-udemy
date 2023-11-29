package com.example.demo.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exception.NoSuchUserException;
import com.example.demo.model.PostStudent;
import com.example.demo.repo.StudentRepository;
import com.example.demo.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository repository;

	@Override
	public Student save(Student student) {
		logger.info("Save student method invoked" + student.toString());
		return repository.save(student);
	}

	@Override
	public Student getStudetById(int id) {
		logger.info("Get student by id student method invoked in service with id" + id);
		return repository.findById(id).orElseThrow(()-> new NoSuchUserException("user not found"));
	}

	@Override
	public Student updateStudent(PostStudent postStudent, int id) {
		logger.info("Update student by id student method invoked in service with id" + id);
		
		Student student = getStudetById(id);
		if(postStudent.getAge()!=0) {
			student.setAge(postStudent.getAge());
		}
		if(postStudent.getName()!=null) {
			student.setName(postStudent.getName());
		}
		if(postStudent.getSalary()!=0) {
			student.setSalary(postStudent.getSalary());
		}
		return repository.save(student);
	}

	@Override
	public String deleteStudentById(int id) {
		logger.info("Delete student by id student method invoked in service with id" + id);
		getStudetById(id);
		repository.deleteById(id);
		return "Student deleted Succesfully";
		
	}

	@Override
	public List<Student> getAllStudent() {
		logger.info("Get all students method invoked in service");
		return repository.findAll();
	}
 
}
