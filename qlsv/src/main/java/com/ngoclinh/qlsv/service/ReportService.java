package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.dto.StudentReportDTO;
import com.ngoclinh.qlsv.entity.Enrollment;
import com.ngoclinh.qlsv.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final EnrollmentRepository enrollmentRepository;

    // 1. Thống kê số lượng sinh viên theo khóa học
    public long countStudentsByCourse(Long courseId) {
        return enrollmentRepository.countByClassRoom_Course_CourseId(courseId);
    }

    // 2. Xuất danh sách sinh viên theo lớp học
    public List<StudentReportDTO> getStudentsByClass(Long classId) {
        List<Enrollment> enrollments = enrollmentRepository.findByClassRoom_ClassId(classId);
        return enrollments.stream().map(e ->
                new StudentReportDTO(e.getStudent().getStudentId(),
                        e.getStudent().getStudentCode(),
                        e.getStudent().getFullName(),
                        e.getGrade())
        ).collect(Collectors.toList());
    }

    // 3. Tính điểm trung bình của từng sinh viên
    public List<StudentReportDTO> getStudentAverageGrades() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::getStudent,
                        Collectors.averagingDouble(e -> e.getGrade() != null ? e.getGrade() : 0.0)
                ))
                .entrySet()
                .stream()
                .map(entry -> new StudentReportDTO(entry.getKey().getStudentId(),
                        entry.getKey().getStudentCode(),
                        entry.getKey().getFullName(),
                        entry.getValue().floatValue()))
                .collect(Collectors.toList());
    }
}
