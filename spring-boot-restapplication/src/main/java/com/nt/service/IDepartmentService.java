package com.nt.service;

import com.nt.dto.DepartmentDto;
import com.nt.util.DepartmentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDepartmentService {

    public DepartmentDto createDepartment(DepartmentDto departmentDto);
    public DepartmentResponse getAllDepartments(Pageable pageable);
    public  DepartmentDto findDepartmentById(Long departmentId);
    public void deleteDepartment(Long departmentId);
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto);

}
