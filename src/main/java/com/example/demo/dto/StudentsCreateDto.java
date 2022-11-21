package com.example.demo.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class StudentsCreateDto {
    @NotEmpty
    @NotNull
    private List<@Valid StudentCreateDto> students;
}
