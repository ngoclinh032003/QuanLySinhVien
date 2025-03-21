package com.ngoclinh.qlsv.repository;

import com.ngoclinh.qlsv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);

    List<Student> findByStudentCodeContainingIgnoreCaseOrFullNameContainingIgnoreCase(String studentCode, String fullName);


    @Query("SELECT COALESCE(AVG(e.grade), 0) FROM Enrollment e WHERE e.student.id = :studentId")
    double calculateAverageGrade(@Param("studentId") Long studentId);
}
