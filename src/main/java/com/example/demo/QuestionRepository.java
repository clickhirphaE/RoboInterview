package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long>{

    Question findByInterview (Interview interview);
    Question findAllByInterviewId (Long id);
}
