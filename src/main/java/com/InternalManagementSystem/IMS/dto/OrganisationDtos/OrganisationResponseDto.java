package com.InternalManagementSystem.IMS.dto.OrganisationDtos;

import lombok.Data;

@Data
public class OrganisationResponseDto {
    String companyName;
    String companyLogoURL;
    String gstIN;
    String pan;
    String businessEmail;
    String businessPhone;
    String adminEmail;
    String officeAddress;
}
