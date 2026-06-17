package com.ibm.demo.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentRequest {

	@NotBlank(message = "Department name must not be blank")
	@Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Location must not be blank")
	@Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
	private String location;

	public DepartmentRequest() {
	}

	public DepartmentRequest(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}