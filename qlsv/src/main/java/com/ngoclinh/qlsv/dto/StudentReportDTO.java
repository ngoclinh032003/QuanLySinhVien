package com.ngoclinh.qlsv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentReportDTO {
    private Long studentId;
    private String studentCode;
    private String fullName;
    private Float averageGrade;
}
