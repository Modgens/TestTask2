package com.example.testingsystem.models.dto.requests.responses;

import lombok.Data;

@Data
public class ExamInfo {
    private String teacherName;
    private String name;
    private Integer count;
    private Integer maxPoint;

}
