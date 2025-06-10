package com.nt.controller;

import com.nt.dto.EmployeeDto;
import com.nt.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

     private final  IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/department/{id}/employee")
    public ResponseEntity<EmployeeDto> saveEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = employeeService.createEmployee(id, employeeDto);
       return  ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("/department/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeByDeptId(@PathVariable Long id){
        List<EmployeeDto> employeeDtos = employeeService.fetchEmployeesByDeptId(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtos);
    }


}
