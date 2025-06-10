package com.nt.repository;

import com.nt.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
