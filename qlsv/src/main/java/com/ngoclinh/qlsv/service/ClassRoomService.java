package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.ClassRoom;
import com.ngoclinh.qlsv.entity.Lecturer;
import com.ngoclinh.qlsv.entity.Student;
import com.ngoclinh.qlsv.repository.ClassRoomRepository;
import com.ngoclinh.qlsv.repository.LecturerRepository;
import com.ngoclinh.qlsv.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    // 1. Tạo lớp học
    public ClassRoom createClass(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    // 2. Lấy danh sách lớp học
    public List<ClassRoom> getAllClasses() {
        return classRoomRepository.findAll();
    }

    // 3. Phân công giảng viên cho lớp
    public ClassRoom assignLecturerToClass(Long classId, Long lecturerId) {
        ClassRoom classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Lớp không tồn tại!"));
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new RuntimeException("Giảng viên không tồn tại!"));
        classRoom.setLecturer(lecturer);
        return classRoomRepository.save(classRoom);
    }

}
