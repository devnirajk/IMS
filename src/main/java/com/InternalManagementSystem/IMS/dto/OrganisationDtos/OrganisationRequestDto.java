package com.InternalManagementSystem.IMS.dto.OrganisationDtos;

import lombok.Data;

@Data
public class OrganisationRequestDto {
    String companyName;
    String companyLogoURL;
    String gstIN;
    String pan;
    String businessEmail;
    String businessPhone;
    String adminEmail;
    String adminPassword;
    String officeAddress;
    String city;
    String state;
    String pincode;
}