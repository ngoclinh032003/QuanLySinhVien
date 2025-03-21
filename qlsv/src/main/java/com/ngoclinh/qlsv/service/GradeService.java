package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.Enrollment;
import com.ngoclinh.qlsv.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final EnrollmentRepository enrollmentRepository;

    public GradeService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // 1️⃣ Nhập điểm cho sinh viên
    public Enrollment addGrade(Long studentId, Long classId, Float grade) {
        Enrollment enrollment = enrollmentRepository.findByStudent_StudentIdAndClassRoom_ClassId(studentId, classId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setGrade(grade);
        return enrollmentRepository.save(enrollment);
    }

    // 2️⃣ Cập nhật điểm
    public Enrollment updateGrade(Long studentId, Long classId, Float newGrade) {
        Enrollment enrollment = enrollmentRepository.findByStudent_StudentIdAndClassRoom_ClassId(studentId, classId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setGrade(newGrade);
        return enrollmentRepository.save(enrollment);
    }

    // 3️⃣ Xem bảng điểm của sinh viên
    public List<Enrollment> getGradesByStudentId(Long studentId) {
        return enrollmentRepository.findByStudent_StudentId(studentId);
    }
}

