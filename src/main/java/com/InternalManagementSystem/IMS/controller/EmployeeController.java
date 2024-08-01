package com.InternalManagementSystem.IMS.controller;

import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeRequestDto;
import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeResponseDto;
import com.InternalManagementSystem.IMS.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.InternalManagementSystem.IMS.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        try {
            EmployeeResponseDto employee = employeeService.createEmployee(employeeRequestDto);
            return ResponseHandler.generateResponse("Employee created successfully!!!", HttpStatus.CREATED, employee);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeResponseDto employee = employeeService.getEmployeeById(id);
            return ResponseHandler.generateResponse("Employee retrieved successfully!!!", HttpStatus.OK, employee);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        try {
            EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, employeeRequestDto);
            return ResponseHandler.generateResponse("Employee updated successfully!!!", HttpStatus.OK, updatedEmployee);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            boolean response = employeeService.deleteEmployee(id);
            return ResponseHandler.generateResponse("Employee deleted successfully!!!", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

