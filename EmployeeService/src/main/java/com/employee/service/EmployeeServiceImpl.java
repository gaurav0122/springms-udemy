package com.employee.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.dto.ApiResponseEmployee;
import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.exception.EmailAlreadyPresentException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.AutoEmployeeMapper;
import com.employee.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

//	private RestTemplate restTemplate;
	private WebClient webClient;
//	private FeignApiClient feignApiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//		Employee employee = new Employee();
//		employee.setEmail(employeeDto.getEmail());
//		employee.setFirstName(employeeDto.getFirstName());
//		employee.setLastName(employeeDto.getLastName());

		Optional<Employee> checkEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

		if (checkEmployee.isPresent()) {
			throw new EmailAlreadyPresentException(employeeDto.getEmail());
		}

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

//	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
	@Override
	public ApiResponseEmployee getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee id", employeeId));
//		EmployeeDto employeeDtoReturn = new EmployeeDto(
//				employee.getId(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail()
//			);
		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);

//		ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/depart/bycode/"+employeeDtoReturn.getDepartmentCode(), DepartmentDto.class);
//		
//		DepartmentDto departmentDto = response.getBody();

		DepartmentDto departmentDto = webClient.get()
				.uri("http://localhost:8080/api/depart/bycode/" + employeeDtoReturn.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();

//		DepartmentDto departmentDto = feignApiClient.getDepartmentById(employeeDtoReturn.getDepartmentCode());

		return new ApiResponseEmployee(employeeDtoReturn, departmentDto);
	}

	public ApiResponseEmployee getDefaultDepartmentResponse(Long employeeId, Exception ex) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee id", employeeId));

		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);

		//ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/depart/bycode/"+employeeDtoReturn.getDepartmentCode(), DepartmentDto.class);
		//
		//DepartmentDto departmentDto = response.getBody();

//		DepartmentDto departmentDto = webClient.get()
//				.uri("http://localhost:8080/api/depart/bycode/" + employeeDtoReturn.getDepartmentCode()).retrieve()
//				.bodyToMono(DepartmentDto.class).block();
		
		DepartmentDto departmentDto = new DepartmentDto(12L, "defaultDepartmentName", "Default Description", "DD01");
		
		//DepartmentDto departmentDto = feignApiClient.getDepartmentById(employeeDtoReturn.getDepartmentCode());
		return new ApiResponseEmployee(employeeDtoReturn, departmentDto);
	}
}
