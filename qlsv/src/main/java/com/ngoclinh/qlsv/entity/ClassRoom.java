package com.ngoclinh.qlsv.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id") // Chỉ định tên cột trong DB là "id"
    private Long classId; // Đặt tên field là classId để dễ đọc code

    @Column(name = "class_name", nullable = false, length = 50)
    private String className;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;
}
