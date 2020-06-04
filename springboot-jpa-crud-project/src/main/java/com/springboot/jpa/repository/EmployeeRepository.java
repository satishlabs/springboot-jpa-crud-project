package com.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
