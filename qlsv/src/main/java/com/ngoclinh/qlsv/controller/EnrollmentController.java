package com.ngoclinh.qlsv.controller;

import com.ngoclinh.qlsv.entity.Enrollment;
import com.ngoclinh.qlsv.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 1️⃣ Lấy danh sách tất cả Enrollment
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // 2️⃣ Tìm Enrollment theo studentId và classId
    @GetMapping("/{studentId}/{classId}")
    public ResponseEntity<Enrollment> getEnrollment(
            @PathVariable Long studentId,
            @PathVariable Long classId) {

        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentByStudentAndClass(studentId, classId);
        return enrollment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3️⃣ Đăng ký khóa học (thêm Enrollment)
    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.addEnrollment(enrollment));
    }

    // 4️⃣ Cập nhật điểm số
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateGrade(@PathVariable Long id, @RequestParam float grade) {
        return ResponseEntity.ok(enrollmentService.updateGrade(id, grade));
    }

    // 5️⃣ Xóa đăng ký học
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Đăng ký sinh viên vào lớp
    @PostMapping("/{studentId}/{classId}")
    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long studentId, @PathVariable Long classId) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, classId));
    }

    // API lấy danh sách sinh viên theo classId
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Enrollment>> getStudentsByClass(@PathVariable Long classId) {
        List<Enrollment> enrollments = enrollmentService.getStudentsByClassId(classId);
        return ResponseEntity.ok(enrollments);
    }
}
