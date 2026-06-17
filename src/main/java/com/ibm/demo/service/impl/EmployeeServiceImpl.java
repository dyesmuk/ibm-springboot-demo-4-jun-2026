package com.ibm.demo.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ibm.demo.dto.employee.EmployeeRequest;
import com.ibm.demo.dto.employee.EmployeeResponse;
import com.ibm.demo.exception.EmailAlreadyExistsException;
import com.ibm.demo.exception.EmployeeNotFoundException;
import com.ibm.demo.mapper.EmployeeMapper;
import com.ibm.demo.model.Department;
import com.ibm.demo.model.Employee;
import com.ibm.demo.repository.EmployeeRepository;
import com.ibm.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeResponse> getAllEmployees() {
		return employeeRepository.findAll().stream().map(EmployeeMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse getEmployeeById(String id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
		return EmployeeMapper.toResponseDTO(employee);
	}

	@Override
	public EmployeeResponse getEmployeeByEmail(String email) {
		Employee employee = employeeRepository.findByEmail(email.toLowerCase())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with email: " + email));
		return EmployeeMapper.toResponseDTO(employee);
	}

	@Override
	public List<EmployeeResponse> getEmployeesByFirstName(String firstName) {
		List<Employee> employees = employeeRepository.findByFirstNameIgnoreCase(firstName.trim());
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees found with first name: " + firstName);
		}
		return employees.stream().map(EmployeeMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse createEmployee(EmployeeRequest requestDTO) {
		String normalizedEmail = requestDTO.getEmail().toLowerCase();
		if (employeeRepository.existsByEmail(normalizedEmail)) {
			throw new EmailAlreadyExistsException("An employee with email " + normalizedEmail + " already exists");
		}
		Employee employee = EmployeeMapper.toEntity(requestDTO);
		Employee saved = employeeRepository.save(employee);
		return EmployeeMapper.toResponseDTO(saved);
	}

	@Override
	public EmployeeResponse updateEmployee(String id, EmployeeRequest requestDTO) {
		Employee existing = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		String normalizedEmail = requestDTO.getEmail().toLowerCase();

		if (!existing.getEmail().equals(normalizedEmail) && employeeRepository.existsByEmail(normalizedEmail)) {
			throw new EmailAlreadyExistsException("An employee with email " + normalizedEmail + " already exists");
		}

		EmployeeMapper.updateEntity(existing, requestDTO);
		Employee updated = employeeRepository.save(existing);
		return EmployeeMapper.toResponseDTO(updated);
	}

	@Override
	public void deleteEmployee(String id) {
		if (!employeeRepository.existsById(id)) {
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}
		employeeRepository.deleteById(id);
	}
}

//package com.ibm.demo.service.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.ibm.demo.exception.EmployeeNotFoundException;
//import com.ibm.demo.exception.EmailAlreadyExistsException;
//import com.ibm.demo.model.Employee;
//import com.ibm.demo.repository.EmployeeRepository;
//import com.ibm.demo.service.EmployeeService;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//	private final EmployeeRepository employeeRepository;
//
//	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
//
//	@Override
//	public List<Employee> getAllEmployees() {		
//		return employeeRepository.findAll();
//	}
//
//	@Override
//	public Employee getEmployeeById(String id) {
////		employeeRepository.findById(id).get(); //
//		return employeeRepository.findById(id)
//				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
//	}
//
//	@Override
//	public Employee getEmployeeByEmail(String email) {
//		return employeeRepository.findByEmail(email)
//				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with email: " + email));
//	}
//
//	@Override
//	public List<Employee> getEmployeesByFirstName(String firstName) {
//		List<Employee> employees = employeeRepository.findByFirstNameIgnoreCase(firstName);
//		if (employees.isEmpty()) {
//			throw new EmployeeNotFoundException("No employees found with first name: " + firstName);
//		}
//		return employees;
//	}
//
//	@Override
//	public Employee createEmployee(Employee employee) {
//		if (employeeRepository.existsByEmail(employee.getEmail())) {
//			throw new EmailAlreadyExistsException("An employee with email " + employee.getEmail() + " already exists");
//		}
//		return employeeRepository.save(employee);
//	}
//
//	@Override
//	public Employee updateEmployee(String id, Employee updatedEmployee) {
//		Employee existing = getEmployeeById(id);
//
//		if (!existing.getEmail().equalsIgnoreCase(updatedEmployee.getEmail())
//				&& employeeRepository.existsByEmail(updatedEmployee.getEmail())) {
//			throw new EmailAlreadyExistsException(
//					"An employee with email " + updatedEmployee.getEmail() + " already exists");
//		}
//
//		existing.setFirstName(updatedEmployee.getFirstName());
//		existing.setLastName(updatedEmployee.getLastName());
//		existing.setEmail(updatedEmployee.getEmail());
//		existing.setSalary(updatedEmployee.getSalary());
//
//		return employeeRepository.save(existing);
//	}
//
//	@Override
//	public void deleteEmployee(String id) {
//		if (!employeeRepository.existsById(id)) {
//			throw new EmployeeNotFoundException("Employee not found with id: " + id);
//		}
//		employeeRepository.deleteById(id);
//	}
//}
