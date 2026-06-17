package com.ibm.demo.service;

import java.util.List;

import com.ibm.demo.dto.department.DepartmentRequest;
import com.ibm.demo.dto.department.DepartmentResponse;

public interface DepartmentService {

	List<DepartmentResponse> getAllDepartments();

	DepartmentResponse getDepartmentById(String id);

	DepartmentResponse getDepartmentByName(String name);

	DepartmentResponse createDepartment(DepartmentRequest requestDTO);

	DepartmentResponse updateDepartment(String id, DepartmentRequest requestDTO);

	void deleteDepartment(String id);
}