package com.InternalManagementSystem.IMS.dto.AuthDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdminAuthDto {
    String email;
    String password;
}
