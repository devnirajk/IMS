package com.InternalManagementSystem.IMS.repository;

import com.InternalManagementSystem.IMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}