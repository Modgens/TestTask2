package com.example.testingsystem.repositories;

import com.example.testingsystem.models.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findAllByTeacherId(Long id);

    @Override
    Page<Exam> findAll(Pageable pageable);

    Page<Exam> findAllByNameIsContainingIgnoreCase(Pageable pageable, String search);
}
