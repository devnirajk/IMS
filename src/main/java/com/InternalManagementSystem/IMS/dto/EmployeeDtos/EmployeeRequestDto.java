package com.InternalManagementSystem.IMS.dto.EmployeeDtos;


import lombok.Data;

@Data
public class EmployeeRequestDto {
    private String employeeId;
    private String profilePicture;
    private String name;
    private String email;
    private String password;
    private String designation;
    private String typeOfEmployment;
    private String department;
    private String role;
}