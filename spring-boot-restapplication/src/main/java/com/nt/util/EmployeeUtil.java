package com.nt.util;


import com.nt.dto.EmployeeDto;
import com.nt.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeUtil {

    public static EmployeeDto mapToDto(Employee employee){

        EmployeeDto employeeDto=new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }

    public  static Employee mapToEntity(EmployeeDto employeeDto){

        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        return employee;
    }
}
