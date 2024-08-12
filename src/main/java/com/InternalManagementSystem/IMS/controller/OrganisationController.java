package com.InternalManagementSystem.IMS.controller;

import com.InternalManagementSystem.IMS.dto.AuthDtos.SuperAdminAuthDto;
import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationRequestDto;
import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationResponseDto;
import com.InternalManagementSystem.IMS.dto.OtpVerificationDto;
import com.InternalManagementSystem.IMS.service.serviceImplementation.JwtService;
import com.InternalManagementSystem.IMS.util.ResponseHandler;
import com.InternalManagementSystem.IMS.service.OrganisationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/organisations")
public class OrganisationController{

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private HttpServletResponse httpServletResponse;

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

    // Route to login (Super Admin level login / Organisation level login)
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SuperAdminAuthDto superAdminAuthDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(superAdminAuthDto.getEmail(), superAdminAuthDto.getPassword()));
        if(authentication.isAuthenticated()){
             Map<String, Object> payload = new HashMap<>();
             String token = jwtService.generateToken(payload, superAdminAuthDto.getEmail());

             Cookie cookie = new Cookie("token", token);
             httpServletResponse.addCookie(cookie);

            return ResponseHandler.generateResponse("Logged in successfully!!!", HttpStatus.OK, true);
        }else{
            return ResponseHandler.generateResponse("Login failed, Email or password is incorrect!!!", HttpStatus.UNAUTHORIZED, false);
        }
    }
}
