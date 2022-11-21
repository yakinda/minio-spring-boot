package com.example.demo.service;

import com.example.demo.dto.StudentCreateDto;
import com.example.demo.dto.StudentUpdateDto;
import com.example.demo.dto.StudentsCreateDto;
import com.example.demo.entity.Student;
import com.example.demo.exceptionhandler.customexception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository repository;

    public Student create(StudentCreateDto student) {
        return repository.save(student.toEntity());
    }

    public Student update(Long id, StudentUpdateDto student) {
        Student foundStudent = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student"));
        return repository.save(student.toEntity(foundStudent));
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            log.error("Student not found");
            throw new ResourceNotFoundException("Student");
        }
        repository.deleteById(id);
    }

    public Student detail(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student"));
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public List<Student> createStudents(StudentsCreateDto students) {
        List<Student> data = students.getStudents().stream().map(StudentCreateDto::toEntity).collect(Collectors.toList());
        return repository.saveAll(data);
    }
}
