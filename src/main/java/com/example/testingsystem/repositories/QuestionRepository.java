package com.example.testingsystem.repositories;

import com.example.testingsystem.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
