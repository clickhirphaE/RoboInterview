package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface InterviewRepository extends CrudRepository<Interview, Long>{
    Interview findById(long id);
}
