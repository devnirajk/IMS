package com.InternalManagementSystem.IMS.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseModel{
    private String employeeId;
    private String name;
    private String profilePicture;
    private String email;
    private String password;
    private String designation;
    private String typeOfEmployment;
    private String department;
    private String role;
}