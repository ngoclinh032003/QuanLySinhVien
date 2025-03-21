package com.ngoclinh.qlsv.repository;

import com.ngoclinh.qlsv.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Tìm danh sách các đăng ký của một sinh viên
    List<Enrollment> findByStudent_StudentId(Long studentId);

    // Tìm danh sách sinh viên theo classId
    List<Enrollment> findByClassRoom_ClassId(Long classId);

    // Tìm đăng ký của sinh viên trong một lớp học
    Optional<Enrollment> findByStudent_StudentIdAndClassRoom_ClassId(Long studentId, Long classId);

    // Đếm số lượng sinh viên đăng ký trong các lớp thuộc một khóa học
    int countByClassRoom_Course_CourseId(Long courseId);
}
