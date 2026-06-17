package com.ibm.demo.mapper;

import com.ibm.demo.dto.employee.EmployeeRequest;
import com.ibm.demo.dto.employee.EmployeeResponse;
import com.ibm.demo.model.Employee;

public class EmployeeMapper {

	private EmployeeMapper() {
	}

	public static Employee toEntity(EmployeeRequest dto) {
		Employee employee = new Employee();
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail().toLowerCase());
		employee.setSalary(dto.getSalary());
		return employee;
	}

	public static void updateEntity(Employee existing, EmployeeRequest dto) {
		existing.setFirstName(dto.getFirstName());
		existing.setLastName(dto.getLastName());
		existing.setEmail(dto.getEmail().toLowerCase());
		existing.setSalary(dto.getSalary());
	}

	public static EmployeeResponse toResponseDTO(Employee employee) {
		return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getSalary(), employee.getCreatedAt(), employee.getUpdatedAt());
	}
}