package com.nt.service;

import com.nt.dto.EmployeeDto;
import com.nt.entity.Department;
import com.nt.entity.Employee;
import com.nt.exception.NoResourceFoundException;
import com.nt.repository.DepartmentRepository;
import com.nt.repository.EmployeeRepository;
import static  com.nt.util.EmployeeUtil.*;


import com.nt.util.EmployeeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements  IEmployeeService{

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public EmployeeServiceImpl(DepartmentRepository departmentRepo, EmployeeRepository employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeDto createEmployee(Long departmentId,EmployeeDto employeeDto) {

        Department department = departmentRepo.findById(departmentId).orElseThrow(() ->
                new NoResourceFoundException("department", "id", departmentId));

        //convert employee dto to entity
        Employee employee = mapToEntity(employeeDto);

        //set department to employee
        employee.setDepartment(department);

        Employee saveEmployee = employeeRepo.save(employee);
        EmployeeDto employeeDto1 = mapToDto(saveEmployee);
        employeeDto1.setDepartmentId(department.getId());
        return  employeeDto1;
    }

    @Override
    public List<EmployeeDto> fetchEmployeesByDeptId(Long departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() ->
                new NoResourceFoundException("department", "id", departmentId));

        List<EmployeeDto> list = department.getEmployees().stream().map(EmployeeUtil::mapToDto).toList();

        System.out.println("All Entities converted to dtos");
        System.out.println("This is from cards branch");

        for(EmployeeDto employeeDto:list){
            employeeDto.setDepartmentId(department.getId());
        }

        return list;
    }
}
