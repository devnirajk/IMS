package com.InternalManagementSystem.IMS.controller;

import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationRequestDto;
import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationResponseDto;
import com.InternalManagementSystem.IMS.dto.OtpVerificationDto;
import com.InternalManagementSystem.IMS.util.ResponseHandler;
import com.InternalManagementSystem.IMS.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return new ResponseEntity<>("1234", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOrganisation(@RequestBody OrganisationRequestDto organisationRequestDto) {
        try {
            OrganisationResponseDto organisation = organisationService.addOrganisation(organisationRequestDto);
            return ResponseHandler.generateResponse("Organisation created successfully!!!", HttpStatus.CREATED, organisation);
        }  catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    // Route to verify email
    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody OtpVerificationDto otpVerificationDto) {
        String response = organisationService.verifyOrganisationEmail(otpVerificationDto.getBusinessEmail());
        return ResponseHandler.generateResponse(response, HttpStatus.OK, null);
    }

    // Route to verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationDto otpVerificationDto) {
        boolean isVerified = organisationService.verifyOtp(otpVerificationDto.getBusinessEmail(), otpVerificationDto.getOtp());
        String response;

        if(isVerified){
            response = "OTP verified successfully!!!";
        } else{
            response = "OTP verification failed!!!";
        }
        return ResponseHandler.generateResponse(response, HttpStatus.OK, isVerified);
    }

}
