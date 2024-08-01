package com.InternalManagementSystem.IMS.service;

import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationRequestDto;
import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface OrganisationService {
    String verifyOrganisationEmail(String email);
    boolean verifyOtp(String email, String otp);
    OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto);
}