package com.ngoclinh.qlsv.controller;

import com.ngoclinh.qlsv.dto.StudentReportDTO;
import com.ngoclinh.qlsv.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 1. Thống kê số lượng sinh viên theo khóa học
    @GetMapping("/students/count/{courseId}")
    public ResponseEntity<Long> countStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(reportService.countStudentsByCourse(courseId));
    }

    // 2. Xuất danh sách sinh viên theo lớp học
    @GetMapping("/students/class/{classId}")
    public ResponseEntity<List<StudentReportDTO>> getStudentsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(reportService.getStudentsByClass(classId));
    }

    // 3. Thống kê điểm trung bình của từng sinh viên
    @GetMapping("/students/average-grades")
    public ResponseEntity<List<StudentReportDTO>> getStudentAverageGrades() {
        return ResponseEntity.ok(reportService.getStudentAverageGrades());
    }
}
