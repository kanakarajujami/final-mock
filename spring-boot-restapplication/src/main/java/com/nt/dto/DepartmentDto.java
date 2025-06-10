package com.nt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "department name should not be null or empty")
    @Size(min = 5,max = 30,message = "department name characters must be  in between 5 and 30")
    private String name;
    @NotBlank(message = "department location should not be null or empty")
    @Size(min = 5,message = "location  characters min 3 characters")
    private String location;
}
