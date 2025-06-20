package com.nt;


import com.nt.dto.DepartmentDto;
import com.nt.entity.Department;
import com.nt.repository.DepartmentRepository;
import com.nt.service.DepartmentServiceImpl;
import com.nt.service.IDepartmentService;
import com.nt.util.DepartmentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {


   @Mock
  // @MockitoBean
    private DepartmentRepository repository;


    /*@Autowired
    private IDepartmentService service;*/

    @InjectMocks
    private DepartmentServiceImpl service;
/*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }*/

    //test createDepartment() method
    @Test
    public void createDepartmentTest(){
        //create department object
        Department departmentOut=new Department(121l,"IT Department","Hyderabad");

        //create department input  entity object
       // Department departmentInput=new Department("IT Department","Hyderabad");

        //provide dummy functionality to DepartmentRepository save() method
        Mockito.when(repository.save(Mockito.any(Department.class))).thenReturn(departmentOut);

        //create Department dto object
        DepartmentDto departmentDto=new DepartmentDto("IT Department","Hyderabad");
        //calling service method
        DepartmentDto department = service.createDepartment(departmentDto);
        Assertions.assertEquals(121l,department.id);
    }

    @Test
    public void testGetAllDepartments(){

        //prepare pageable object
        Pageable pageable= PageRequest.of(0,3);

        //prepare list departments
        Department department1=new Department(121l,"Chennai","it department");
        Department department2=new Department(122l,"Hyderabad","Electronic department");

        List<Department> departmentList=List.of(department1,department2);

        //prepare page object
        Page<Department> page=new PageImpl<>(departmentList);

        //provide dummy functionality to findAll() of department repository
        Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        //calling service
        DepartmentResponse allDepartments = service.getAllDepartments(pageable);

        Assertions.assertEquals(2,allDepartments.getDepartmentDtoList().size());
        Assertions.assertEquals(0,allDepartments.getPageNo());
        Assertions.assertEquals(2,allDepartments.getPageSize());
        Assertions.assertEquals(2,allDepartments.getTotalElements());
        Assertions.assertEquals(1,allDepartments.getTotalPages());


    }
}
