package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.Student;
import com.ngoclinh.qlsv.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Kích hoạt Mockito
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;  // Giả lập repository

    @InjectMocks
    private StudentService studentService;  // Inject repository vào service

    private Student student1, student2;

    @BeforeEach
    void setUp() {
        student1 = new Student(1L, "SV001", "Nguyễn Văn A", "a@example.com", "0901234567", null,null);
        student2 = new Student(2L, "SV002", "Trần Thị B", "b@example.com", "0912345678", null,null);
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(student1)).thenReturn(student1);

        Student savedStudent = studentService.addStudent(student1);

        assertNotNull(savedStudent);
        assertEquals("SV001", savedStudent.getStudentCode());
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    void testFindStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));

        Student foundStudent = studentService.getStudentById(1L);

        assertNotNull(foundStudent);
        assertEquals("Nguyễn Văn A", foundStudent.getFullName());
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testUpdateStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
        when(studentRepository.save(any(Student.class))).thenReturn(student1);

        Student updatedStudent = new Student(1L, "SV001", "Nguyễn Văn A - Updated", "a@example.com", "0901234567", null,null);
        Student result = studentService.updateStudent(1L, updatedStudent);

        assertEquals("Nguyễn Văn A - Updated", result.getFullName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

}
