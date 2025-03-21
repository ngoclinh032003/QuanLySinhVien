package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.Student;
import com.ngoclinh.qlsv.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    public Student getStudentByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new RuntimeException("Student not found with StudentCode: " + studentCode));
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student s = student.get();
            s.setFullName(studentDetails.getFullName());
            s.setEmail(studentDetails.getEmail());
            s.setPhone(studentDetails.getPhone());
            s.setDateOfBirth(studentDetails.getDateOfBirth());
            return studentRepository.save(s);
        }
        return null;
    }

    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) { // Kiểm tra xem sinh viên có tồn tại không
            studentRepository.deleteById(id);
            return true; // Xóa thành công
        }
        return false; // Không tìm thấy sinh viên để xóa
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchStudents(String keyword) {
        return studentRepository.findByStudentCodeContainingIgnoreCaseOrFullNameContainingIgnoreCase(keyword, keyword);
    }

}
