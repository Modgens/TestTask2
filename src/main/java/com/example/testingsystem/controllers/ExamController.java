package com.example.testingsystem.controllers;

import com.example.testingsystem.models.Exam;
import com.example.testingsystem.models.dto.requests.ExamRequest;
import com.example.testingsystem.models.dto.responses.ExamForPassing;
import com.example.testingsystem.models.dto.responses.ExamInfo;
import com.example.testingsystem.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService service;

    @PostMapping("/newExam")
    public void register(@RequestBody ExamRequest request) {
        service.save(request);
    }
    @GetMapping("/getTeachersTests")
    public ResponseEntity<List<ExamInfo>> getAllForTeacher(@RequestParam Long teacherId){
        return ResponseEntity.ok(service.getAllForTeacher(teacherId));
    }
    @GetMapping("/getAllTests")
    public ResponseEntity<Page<ExamInfo>> getAllForStudent(@RequestParam int page, @RequestParam int limit, @RequestParam String search){
        return ResponseEntity.ok(service.getAll(page, limit, search));
    }
    @GetMapping("/exam/{id}")
    public ResponseEntity<ExamForPassing> getExamById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
}
