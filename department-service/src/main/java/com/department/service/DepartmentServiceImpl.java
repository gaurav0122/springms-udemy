package com.department.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository departmentRepository;
	
	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) {
		Department department = new Department();
		department.setDepartmentCode(departmentDto.getDepartmentCode());
		department.setDepartmentDescription(departmentDto.getDepartmentDescription());
		department.setDepartmentName(departmentDto.getDepartmentName());
		
		Department savedDepartment =  departmentRepository.save(department);
		
		DepartmentDto departmentDtoReturn = new DepartmentDto(
				savedDepartment.getId(),
				savedDepartment.getDepartmentName(),
				savedDepartment.getDepartmentDescription(),
				savedDepartment.getDepartmentCode()
				);
		
		return departmentDtoReturn;
	}
	
	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		Department department = departmentRepository.findByDepartmentCode(departmentCode);
		DepartmentDto departmentDto= new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
				);
		return departmentDto;
	}


	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		Department department = departmentRepository.findById(departmentId).get();
		DepartmentDto departmentDto = new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
				); 
		return departmentDto;
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		List<Department> list = departmentRepository.findAll();
		
		
		List<DepartmentDto> dtoList =list.stream().map((department)->{
			return new DepartmentDto(
					department.getId(),
					department.getDepartmentName(),
					department.getDepartmentDescription(),
					department.getDepartmentCode()
					);
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
		Department department = departmentRepository.findById(departmentDto.getId()).get();
		
		department.setDepartmentCode(departmentDto.getDepartmentCode());
		department.setDepartmentDescription(departmentDto.getDepartmentDescription());
		department.setDepartmentName(departmentDto.getDepartmentName());
		DepartmentDto departmentDtoReturn = new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
				); 
		return departmentDtoReturn;
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		
		departmentRepository.deleteById(departmentId);
		
	}

	
}
