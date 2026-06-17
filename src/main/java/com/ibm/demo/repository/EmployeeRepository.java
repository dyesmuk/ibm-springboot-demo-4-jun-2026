package com.ibm.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.Employee;
@RepositoryRestResource(exported = false)
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Optional<Employee> findByEmail(String email);

	List<Employee> findByFirstNameIgnoreCase(String firstName);

	boolean existsByEmail(String email);

	List<Employee> findByDepartmentId(String departmentId);

	boolean existsByDepartmentId(String departmentId);

	List<Employee> findByProjectIdsContaining(String projectId);

	boolean existsByProjectIdsContaining(String projectId);
}

//package com.ibm.demo.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import com.ibm.demo.model.Employee;
//
//@Repository
//public interface EmployeeRepository extends MongoRepository<Employee, String> {
//
//    Optional<Employee> findByEmail(String email);
//
//    List<Employee> findByFirstNameIgnoreCase(String firstName);
//
////    List<Employee> findByPhone(long phone);
//
//    boolean existsByEmail(String email);
//}
