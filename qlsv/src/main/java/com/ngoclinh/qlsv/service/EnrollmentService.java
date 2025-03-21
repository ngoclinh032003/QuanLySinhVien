package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.ClassRoom;
import com.ngoclinh.qlsv.entity.Enrollment;
import com.ngoclinh.qlsv.entity.Student;
import com.ngoclinh.qlsv.repository.ClassRoomRepository;
import com.ngoclinh.qlsv.repository.EnrollmentRepository;
import com.ngoclinh.qlsv.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             ClassRoomRepository classRoomRepository,
                             StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.classRoomRepository = classRoomRepository;
        this.studentRepository = studentRepository;
    }

    // 1️⃣ Lấy danh sách tất cả đăng ký học
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // 2️⃣ Tìm đăng ký theo studentId và classId
    public Optional<Enrollment> getEnrollmentByStudentAndClass(Long studentId, Long classId) {
        return enrollmentRepository.findByStudent_StudentIdAndClassRoom_ClassId(studentId, classId);
    }

    // 3️⃣ Đăng ký khóa học (thêm Enrollment)
    public Enrollment addEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // 4️⃣ Cập nhật điểm số
    public Enrollment updateGrade(Long enrollmentId, float grade) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setGrade(grade);
            return enrollmentRepository.save(enrollment);
        }
        throw new RuntimeException("Enrollment not found with id: " + enrollmentId);
    }

    // 5️⃣ Xóa đăng ký học
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    // 6 Đăng ký sinh viên vào lớp
    public Enrollment enrollStudent(Long studentId, Long classId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên!"));
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp!"));

        // Kiểm tra xem sinh viên đã đăng ký lớp chưa
        if (enrollmentRepository.findByStudent_StudentIdAndClassRoom_ClassId(studentId, classId).isPresent()) {
            throw new RuntimeException("Sinh viên đã đăng ký lớp này rồi!");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .classRoom(classRoom)
                .build();

        return enrollmentRepository.save(enrollment);
    }

    // Lấy danh sách sinh viên theo classId
    public List<Enrollment> getStudentsByClassId(Long classId) {
        return enrollmentRepository.findByClassRoom_ClassId(classId);
    }
}

