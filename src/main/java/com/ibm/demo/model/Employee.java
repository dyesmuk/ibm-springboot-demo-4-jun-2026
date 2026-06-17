package com.ibm.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
public class Employee {

	@Id
	private String id;

	@Field("first_name")
	private String firstName;

	@Field("last_name")
	private String lastName;

	@Indexed(unique = true)
	private String email;

	private double salary;

	// ManyToOne
	@Field("department_id")
	private String departmentId;

	// ManyToMany
	@Field("project_ids")
	private List<String> projectIds = new ArrayList<>();

	@CreatedDate
	@Field("created_at")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Field("updated_at")
	private LocalDateTime updatedAt;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String email, double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
	}

	public Employee(String firstName, String lastName, String email, double salary, String departmentId,
			List<String> projectIds) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.departmentId = departmentId;
		this.projectIds = projectIds != null ? projectIds : new ArrayList<>();
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", salary=" + salary + ", departmentId=" + departmentId + ", projectIds=" + projectIds
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}

//package com.ibm.demo.model;
//
//import java.time.LocalDateTime;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//@Document(collection = "employees")
//public class Employee {
//
//	@Id
//	private String id;
//
//	@Field("first_name")
//	private String firstName;
//
//	@Field("last_name")
//	private String lastName;
//
//	@Indexed(unique = true)
//	private String email;
//
//	@Field("salary")
//	private double salary;
//
//	@CreatedDate
//	@Field("created_at")
//	private LocalDateTime createdAt;
//
//	@LastModifiedDate
//	@Field("updated_at")
//	private LocalDateTime updatedAt;
//
//	public Employee() {
//	}
//
//	public Employee(String firstName, String lastName, String email, double salary) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.salary = salary;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public double getSalary() {
//		return salary;
//	}
//
//	public void setSalary(double salary) {
//		this.salary = salary;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", salary=" + salary + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
//	}
//}
//
//////EmployeeRequestDTO - client sends  
//////EmployeeResponseDTO - server returns 
//////Employee - model class  
//////
//////EmployeeMapper class
//////
//////EmployeeService 
//////EmployeeServiceImpl  
//////
//////EmployeeController
////
////
////
////
////
//////EmployeeRequestDTO - client sends - firstName, lastName, email, salary 
//////EmployeeResponseDTO - server returns - id, firstName, lastName, email, salary
//////Employee model - id, firstName, lastName, email, salary
//////@Document, @Id, @Field, @Indexed
//////EmployeeMapper class
//////RequestDTO to Employee to save 
//////Employee to ResponseDTO to return 
//////EmployeeService interface + impl
//////EmployeeController
////
////
////package com.ibm.demo.model;
////
////import jakarta.validation.constraints.Email;
////import jakarta.validation.constraints.NotBlank;
////import jakarta.validation.constraints.NotNull;
////import jakarta.validation.constraints.Positive;
////import jakarta.validation.constraints.Size;
////
////import java.time.LocalDateTime;
////
////import org.springframework.data.annotation.Id;
////import org.springframework.data.mongodb.core.index.Indexed;
////import org.springframework.data.mongodb.core.mapping.Document;
////import org.springframework.data.mongodb.core.mapping.Field;
////
//////validations constraints  
//////https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary
////
////@Document(collection = "employees")
////public class Employee {
////
////	@Id
////	private String id;
////
////	@NotBlank(message = "First name must not be blank")
////	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
////	@Field("first_name")
////	private String firstName;
////
////	@NotBlank(message = "Last name must not be blank")
////	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
////	@Field("last_name")
////	private String lastName;
////
////	@NotBlank(message = "Email must not be blank")
////	@Email(message = "Email must be a valid email address")
////	@Indexed(unique = true)
////	private String email;
////
////	@NotNull(message = "Salary must not be null")
////	@Positive(message = "Salary must be a positive value")
////	private double salary;
////	
////    private LocalDateTime createdAt;
////    private LocalDateTime updatedAt;
////
////	public Employee() {
////	}
////
////	public Employee(String firstName, String lastName, String email, double salary) {
////		this.firstName = firstName;
////		this.lastName = lastName;
////		this.email = email;
////		this.salary = salary;
////	}
////
////	public String getId() {
////		return id;
////	}
////
////	public void setId(String id) {
////		this.id = id;
////	}
////
////	public String getFirstName() {
////		return firstName;
////	}
////
////	public void setFirstName(String firstName) {
////		this.firstName = firstName;
////	}
////
////	public String getLastName() {
////		return lastName;
////	}
////
////	public void setLastName(String lastName) {
////		this.lastName = lastName;
////	}
////
////	public String getEmail() {
////		return email;
////	}
////
////	public void setEmail(String email) {
////		this.email = email;
////	}
////
////	public double getSalary() {
////		return salary;
////	}
////
////	public void setSalary(double salary) {
////		this.salary = salary;
////	}
////
////	@Override
////	public String toString() {
////		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
////				+ ", salary=" + salary + "]";
////	}
////
////}
////
////
////
////
//////package com.ibm.demo.model;
//////
//////import jakarta.validation.constraints.Email;
//////import jakarta.validation.constraints.NotBlank;
//////import jakarta.validation.constraints.NotNull;
//////import jakarta.validation.constraints.Positive;
//////import jakarta.validation.constraints.Size;
//////
//////import org.springframework.data.annotation.Id;
//////import org.springframework.data.mongodb.core.index.Indexed;
//////import org.springframework.data.mongodb.core.mapping.Document;
//////import org.springframework.data.mongodb.core.mapping.Field;
//////
////////validations constraints  
////////https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary
//////
//////@Document(collection = "employees")
//////public class Employee {
//////
//////	@Id
//////	private String id;
//////
//////	@NotBlank(message = "First name must not be blank")
//////	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
//////	@Field("first_name")
//////	private String firstName;
//////
//////	@NotBlank(message = "Last name must not be blank")
//////	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
//////	@Field("last_name")
//////	private String lastName;
//////
//////	@NotBlank(message = "Email must not be blank")
//////	@Email(message = "Email must be a valid email address")
//////	@Indexed(unique = true)
//////	private String email;
//////
//////	@NotNull(message = "Salary must not be null")
//////	@Positive(message = "Salary must be a positive value")
//////	private double salary;
//////
//////	public Employee() {
//////	}
//////
//////	public Employee(String firstName, String lastName, String email, double salary) {
//////		this.firstName = firstName;
//////		this.lastName = lastName;
//////		this.email = email;
//////		this.salary = salary;
//////	}
//////
//////	public String getId() {
//////		return id;
//////	}
//////
//////	public void setId(String id) {
//////		this.id = id;
//////	}
//////
//////	public String getFirstName() {
//////		return firstName;
//////	}
//////
//////	public void setFirstName(String firstName) {
//////		this.firstName = firstName;
//////	}
//////
//////	public String getLastName() {
//////		return lastName;
//////	}
//////
//////	public void setLastName(String lastName) {
//////		this.lastName = lastName;
//////	}
//////
//////	public String getEmail() {
//////		return email;
//////	}
//////
//////	public void setEmail(String email) {
//////		this.email = email;
//////	}
//////
//////	public double getSalary() {
//////		return salary;
//////	}
//////
//////	public void setSalary(double salary) {
//////		this.salary = salary;
//////	}
//////
//////	@Override
//////	public String toString() {
//////		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//////				+ ", salary=" + salary + "]";
//////	}
//////
//////}
