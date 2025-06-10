package com.nt.controller;

import com.nt.dto.DepartmentDto;
import com.nt.entity.Department;
import com.nt.service.IDepartmentService;
import com.nt.util.DepartmentResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/department/api")
public class DepartmentController {

    private final  IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> saveDepartment( @Valid  @RequestBody DepartmentDto departmentDto){
        //use service
        DepartmentDto department = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(department, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<DepartmentResponse> fetchAllDepartments(
            @RequestParam(defaultValue ="0",required = false ) Integer pageNo,
            @RequestParam(defaultValue = "5",required = false) Integer pageSize,
            @RequestParam(defaultValue = "id",required = false) String sortBy,
            @RequestParam(defaultValue = "asc",required = false) String sortDir
    ){


        Sort orders = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize, orders);
        DepartmentResponse allDepartments = departmentService.getAllDepartments(pageable);
        return new ResponseEntity<DepartmentResponse>(allDepartments,HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<DepartmentDto> fetchDepartmentById(@PathVariable Long id){

         //use service
        DepartmentDto departmentById = departmentService.findDepartmentById(id);
        return new ResponseEntity<DepartmentDto>(departmentById,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        //use service
        departmentService.deleteDepartment(id);
        return new ResponseEntity<String>("department deleted successfully",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> updateDepartment( @Valid @RequestBody DepartmentDto departmentDto){
        //user service
        DepartmentDto departmentDto1 = departmentService.updateDepartment(departmentDto);
        return  ResponseEntity.status(HttpStatus.OK).body(departmentDto1);

    }
}
