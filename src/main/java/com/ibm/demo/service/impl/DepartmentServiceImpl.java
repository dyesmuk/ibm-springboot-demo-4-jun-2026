package com.ibm.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ibm.demo.dto.department.DepartmentRequest;
import com.ibm.demo.dto.department.DepartmentResponse;
import com.ibm.demo.exception.DepartmentAlreadyExistsException;
import com.ibm.demo.exception.DepartmentNotFoundException;
import com.ibm.demo.mapper.DepartmentMapper;
import com.ibm.demo.model.Department;
import com.ibm.demo.repository.DepartmentRepository;
import com.ibm.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<DepartmentResponse> getAllDepartments() {

		return departmentRepository.findAll().stream().map(DepartmentMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentResponse getDepartmentById(String id) {

		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));

		return DepartmentMapper.toResponseDTO(department);
	}

	@Override
	public DepartmentResponse getDepartmentByName(String name) {

		Department department = departmentRepository.findByName(name.trim())
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found with name: " + name));

		return DepartmentMapper.toResponseDTO(department);
	}

	@Override
	public DepartmentResponse createDepartment(DepartmentRequest requestDTO) {

		String departmentName = requestDTO.getName().trim();

		if (departmentRepository.existsByName(departmentName)) {
			throw new DepartmentAlreadyExistsException("Department already exists with name: " + departmentName);
		}

		Department department = DepartmentMapper.toEntity(requestDTO);

		Department savedDepartment = departmentRepository.save(department);

		return DepartmentMapper.toResponseDTO(savedDepartment);
	}

	@Override
	public DepartmentResponse updateDepartment(String id, DepartmentRequest requestDTO) {

		Department existingDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));

		String departmentName = requestDTO.getName().trim();

		if (!existingDepartment.getName().equalsIgnoreCase(departmentName)
				&& departmentRepository.existsByName(departmentName)) {

			throw new DepartmentAlreadyExistsException("Department already exists with name: " + departmentName);
		}

		DepartmentMapper.updateEntity(existingDepartment, requestDTO);

		Department updatedDepartment = departmentRepository.save(existingDepartment);

		return DepartmentMapper.toResponseDTO(updatedDepartment);
	}

	@Override
	public void deleteDepartment(String id) {

		if (!departmentRepository.existsById(id)) {
			throw new DepartmentNotFoundException("Department not found with id: " + id);
		}

		departmentRepository.deleteById(id);
	}
}