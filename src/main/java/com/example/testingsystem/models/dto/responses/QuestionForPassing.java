package com.example.testingsystem.models.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionForPassing {
    private String question;
    private List<String> options;
    private Float point;
}
