package com.example.demo.dto;

import com.example.demo.entity.Student;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class StudentUpdateDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer age;

    private String email;

    private String address;

    private String avatar;

    public Student toEntity(Student student) {
        student.setName(this.name);
        student.setAge(this.age);
        student.setAddress(this.address);
        student.setEmail(this.email);
        student.setAvatar(this.avatar);
        student.setUpdateAt(LocalDateTime.now());
        return student;
    }
}
