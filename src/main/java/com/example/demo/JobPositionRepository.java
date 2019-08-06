package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface JobPositionRepository extends CrudRepository<JobPosition, Long> {
    JobPosition findById(long id);
}
