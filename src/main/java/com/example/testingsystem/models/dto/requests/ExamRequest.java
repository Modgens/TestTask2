package com.example.testingsystem.models.dto.requests;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ExamRequest {
    private Long teacherId;
    private String testName;
    private List<QuestionRequest> questionBlocks;
    private Integer maxPoints;
}
