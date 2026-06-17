package com.ibm.demo.dto.employee;

import java.time.LocalDateTime;

/**
 * Outgoing data sent to the client. No validation annotations — this is
 * read-only response data. No MongoDB annotations — this is a plain Java
 * object. Exposes exactly what the client needs, nothing more.
 */
public class EmployeeResponse {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Double salary;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public EmployeeResponse() {
	}

	public EmployeeResponse(String id, String firstName, String lastName, String email, Double salary,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeResponse [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", salary=" + salary + "]";
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
