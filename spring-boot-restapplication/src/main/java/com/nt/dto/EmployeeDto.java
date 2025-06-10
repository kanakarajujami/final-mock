package com.nt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    @NotBlank(message = "employee name should not be null or empty")
    @Size(min=5,message = "employee name minimum five characters")
    private String name;
    @NotBlank(message = "employee designation  should not be null or empty")
    @Size(min=5,message = "employee designation  minimum five characters")
    private String designation;
    private Long departmentId;
}
