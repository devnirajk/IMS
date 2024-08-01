package com.InternalManagementSystem.IMS.service;

import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeRequestDto;
import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(Long id);
    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);
    boolean deleteEmployee(Long id);
}
