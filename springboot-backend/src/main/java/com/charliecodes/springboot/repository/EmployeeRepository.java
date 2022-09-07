package com.charliecodes.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charliecodes.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
