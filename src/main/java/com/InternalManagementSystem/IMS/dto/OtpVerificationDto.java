package com.InternalManagementSystem.IMS.dto;

import lombok.Data;

@Data
public class OtpVerificationDto {
    private String businessEmail;
    private String otp;
}
