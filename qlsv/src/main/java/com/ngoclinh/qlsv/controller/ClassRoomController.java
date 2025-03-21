package com.ngoclinh.qlsv.controller;

import com.ngoclinh.qlsv.entity.ClassRoom;
import com.ngoclinh.qlsv.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    // 1. Tạo lớp học
    @PostMapping
    public ResponseEntity<ClassRoom> createClass(@RequestBody ClassRoom classRoom) {
        return ResponseEntity.ok(classRoomService.createClass(classRoom));
    }

    // 2. Lấy danh sách lớp học
    @GetMapping
    public ResponseEntity<List<ClassRoom>> getAllClasses() {
        return ResponseEntity.ok(classRoomService.getAllClasses());
    }

    // 3. Phân công giảng viên cho lớp
    @PostMapping("/{classId}/assign-lecturer/{lecturerId}")
    public ResponseEntity<ClassRoom> assignLecturerToClass(@PathVariable Long classId, @PathVariable Long lecturerId) {
        return ResponseEntity.ok(classRoomService.assignLecturerToClass(classId, lecturerId));
    }

}
