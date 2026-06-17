package com.ibm.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ibm.demo.model.Project;

@RepositoryRestResource(path = "projects")
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}