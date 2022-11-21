package com.example.demo.controller;

import com.example.demo.dto.StudentCreateDto;
import com.example.demo.dto.StudentUpdateDto;
import com.example.demo.dto.StudentsCreateDto;
import com.example.demo.entity.Student;
import com.example.demo.factory.ResponseFactory;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "student")
public class StudentController {
    private final StudentService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StudentCreateDto student) {
        Student data = service.create(student);
        return ResponseFactory.success(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentUpdateDto student) {
        Student data = service.update(id, student);
        return ResponseFactory.success(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Student data = service.detail(id);
        return ResponseFactory.success(data);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Student> students = service.getAll();
        return ResponseFactory.success(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseFactory.success();
    }

    @PostMapping("/multiple")
    public ResponseEntity<?> createStudents(@Valid @RequestBody StudentsCreateDto students) {
        return ResponseFactory.success(service.createStudents(students));
    }

}
