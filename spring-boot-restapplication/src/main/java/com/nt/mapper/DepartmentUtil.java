package com.nt.mapper;

import com.nt.dto.DepartmentDto;
import com.nt.entity.Department;
import org.springframework.beans.BeanUtils;

public class DepartmentUtil {

    public static DepartmentDto mapToDto(Department department){

        DepartmentDto departmentDto=new DepartmentDto();
        BeanUtils.copyProperties(department,departmentDto);
        return departmentDto;
    }

    public static Department mapToEntity(DepartmentDto departmentDto){

        Department department=new Department();
        BeanUtils.copyProperties(departmentDto,department);
        return department;
    }
}
