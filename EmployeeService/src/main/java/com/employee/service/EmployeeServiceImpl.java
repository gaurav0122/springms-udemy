package com.employee.service;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto employeeDtoReturn = new EmployeeDto(
					savedEmployee.getId(),
					savedEmployee.getFirstName(),
					savedEmployee.getLastName(),
					savedEmployee.getEmail()
				);
		
		return employeeDtoReturn;
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		EmployeeDto employeeDtoReturn = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail()
			);
		return employeeDtoReturn;
	}

}
