package com.example.testingsystem.models.dto.responses;

import com.example.testingsystem.models.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExamForPassing {
    private Long id;
    private String name;
    private String teacherName;
    private Integer maxPoint;
    private List<QuestionForPassing> list;

}
