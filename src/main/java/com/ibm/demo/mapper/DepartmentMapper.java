package com.ibm.demo.mapper;

import com.ibm.demo.dto.department.DepartmentRequest;
import com.ibm.demo.dto.department.DepartmentResponse;
import com.ibm.demo.model.Department;

public class DepartmentMapper {

	private DepartmentMapper() {
	}

	public static Department toEntity(DepartmentRequest dto) {

		Department department = new Department();

		department.setName(dto.getName().trim());
		department.setLocation(dto.getLocation().trim());

		return department;
	}

	public static void updateEntity(Department department, DepartmentRequest dto) {

		department.setName(dto.getName().trim());
		department.setLocation(dto.getLocation().trim());
	}

	public static DepartmentResponse toResponseDTO(Department department) {

		return new DepartmentResponse(
				department.getId(),
				department.getName(),
				department.getLocation(),
				department.getCreatedAt(),
				department.getUpdatedAt());
	}
}