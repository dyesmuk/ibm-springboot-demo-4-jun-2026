package com.ibm.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

	Optional<Project> findByName(String name);

	boolean existsByName(String name);

	List<Project> findByIdIn(List<String> ids);

	List<Project> findByEmployeeIdsContaining(String employeeId);

	boolean existsByEmployeeIdsContaining(String employeeId);
}