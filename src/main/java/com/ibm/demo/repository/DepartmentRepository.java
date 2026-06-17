package com.ibm.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.Department;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

	Optional<Department> findByName(String name);

	boolean existsByName(String name);
}