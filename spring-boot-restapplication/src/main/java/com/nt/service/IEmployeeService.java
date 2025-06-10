package com.nt.service;

import com.nt.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    public EmployeeDto createEmployee(Long departmentId,EmployeeDto employeeDto);
    public List<EmployeeDto> fetchEmployeesByDeptId(Long departmentId);
}
