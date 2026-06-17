package com.ibm.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.Project;

@RepositoryRestResource(path = "projects")
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

	public abstract List<Project> findByName(String name);

	public abstract List<Project> findByStartDate(LocalDateTime date);

}