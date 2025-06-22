package com.nt.service;

import com.nt.dto.DepartmentDto;
import com.nt.entity.Department;

import com.nt.exception.NoResourceFoundException;
import com.nt.mapper.DepartmentUtil;
import com.nt.repository.DepartmentRepository;
import com.nt.util.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nt.mapper.DepartmentUtil.mapToDto;
import static com.nt.mapper.DepartmentUtil.mapToEntity;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService{

    private final DepartmentRepository departmentRepo;

    public DepartmentServiceImpl(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {


        System.out.println("From service branch createDepartment()");
        System.out.println("From service branch createDepartment()");
        Department savedDepartment =departmentRepo.save(mapToEntity(departmentDto));
        return mapToDto(savedDepartment);
    }

    @Override
    public DepartmentResponse getAllDepartments(Pageable pageable) {
        Page<Department> all = departmentRepo.findAll(pageable);

        System.out.println("Get all departments");
        DepartmentResponse departmentResponse=new DepartmentResponse();
        departmentResponse.setDepartmentDtoList(all.stream().map(DepartmentUtil::mapToDto).toList());
        departmentResponse.setPageNo(all.getNumber());
        departmentResponse.setPageSize(all.getSize());
        departmentResponse.setTotalPages(all.getTotalPages());
        departmentResponse.setTotalElements(all.getNumberOfElements());
        //map entity to dto and return
       return departmentResponse;

    }

    @Override
    public DepartmentDto findDepartmentById(Long departmentId) {

        Department department = departmentRepo.findById(departmentId).orElseThrow(() ->
                               new NoResourceFoundException("department", "id", departmentId));
        return mapToDto(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {

        Department department = departmentRepo.findById(departmentId).orElseThrow(() ->
                new NoResourceFoundException("department", "id", departmentId));

        departmentRepo.delete(department);

    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepo.findById(departmentDto.getId()).orElseThrow(() ->
                new NoResourceFoundException("department", "id", departmentDto.getId()));

                 department.setName(departmentDto.getName());
                 department.setLocation(departmentDto.getLocation());

                 //save department
                 Department save = departmentRepo.save(department);
        return mapToDto(save);
    }
}
