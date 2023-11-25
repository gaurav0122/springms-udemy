package com.employee.service;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
//		Employee employee = new Employee();
//		employee.setEmail(employeeDto.getEmail());
//		employee.setFirstName(employeeDto.getFirstName());
//		employee.setLastName(employeeDto.getLastName());
		
		Employee employee = AutoEmployeeMapper.MAPPER.maptoEmployee(employeeDto);
		
		Employee savedEmployee = employeeRepository.save(employee);
//		
//		EmployeeDto employeeDtoReturn = new EmployeeDto(
//					savedEmployee.getId(),
//					savedEmployee.getFirstName(),
//					savedEmployee.getLastName(),
//					savedEmployee.getEmail()
//				);
		
		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(savedEmployee);
		return employeeDtoReturn;
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
//		EmployeeDto employeeDtoReturn = new EmployeeDto(
//				employee.getId(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail()
//			);
		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);
		return employeeDtoReturn;
	}

}
