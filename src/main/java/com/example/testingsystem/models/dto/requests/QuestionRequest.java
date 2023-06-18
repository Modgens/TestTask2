package com.example.testingsystem.models.dto.requests;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuestionRequest {
    private String question;
    private List<String> answers;
    private String correctAnswer;
    private Float points;
}
