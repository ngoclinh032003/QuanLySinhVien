package com.ngoclinh.qlsv.service;

import com.ngoclinh.qlsv.entity.Lecturer;
import com.ngoclinh.qlsv.repository.LecturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Optional<Lecturer> getLecturerById(Long id) {
        return lecturerRepository.findById(id);
    }

    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public Lecturer updateLecturer(Long id, Lecturer updatedLecturer) {
        return lecturerRepository.findById(id).map(lecturer -> {
            lecturer.setFullName(updatedLecturer.getFullName());
            lecturer.setEmail(updatedLecturer.getEmail());
            lecturer.setPhone(updatedLecturer.getPhone());
            return lecturerRepository.save(lecturer);
        }).orElseThrow(() -> new RuntimeException("Lecturer not found"));
    }

    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }
}
