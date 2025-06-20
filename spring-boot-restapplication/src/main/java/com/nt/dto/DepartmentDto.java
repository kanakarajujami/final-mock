package com.nt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(force = true)
public class DepartmentDto {
    public Long id;
    @NotBlank(message = "department name should not be null or empty")
    @Size(min = 5,max = 30,message = "department name characters must be  in between 5 and 30")
    public String name;
    @NotBlank(message = "department location should not be null or empty")
    @Size(min = 5,message = "location  characters min 3 characters")
    public   String location;



    public DepartmentDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public DepartmentDto(Long id,String name, String location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }

    public DepartmentDto() {

    }
}
