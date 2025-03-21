package com.ngoclinh.qlsv.repository;

import com.ngoclinh.qlsv.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
