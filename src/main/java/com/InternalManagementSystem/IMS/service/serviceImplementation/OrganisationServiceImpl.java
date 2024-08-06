package com.InternalManagementSystem.IMS.service.serviceImplementation;

import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationRequestDto;
import com.InternalManagementSystem.IMS.dto.OrganisationDtos.OrganisationResponseDto;
import com.InternalManagementSystem.IMS.entity.Organisation;
import com.InternalManagementSystem.IMS.repository.OrganisationRepository;
import com.InternalManagementSystem.IMS.service.EmailService;
import com.InternalManagementSystem.IMS.service.OrganisationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class OrganisationServiceImpl implements OrganisationService{

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UploadService uploadService;

    @Autowired
    EmailService emailService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String verifyOrganisationEmail(String email){
        String otp = String.valueOf((long)(Math.random()*10000));
        if(otp.length() < 4){
            otp+=1;
        }
        redisTemplate.opsForValue().set(email, otp);
        return emailService.sendSimpleMail(email, otp);
    }

    @Override
    public boolean verifyOtp(String email, String otp){
        String savedOtp = redisTemplate.opsForValue().get(email).toString();

        if(savedOtp.equals(otp)){
            return true;
        } else{
            return false;
        }
    }

     @Override
     public OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto) {
        try {
        // Map the DTO to the entity
        Organisation organisation = modelMapper.map(organisationRequestDto, Organisation.class);

        organisation.setCreatedAt(new Date());
        organisation.setUpdatedAt(new Date());
        organisation.setAdminPassword(passwordEncoder.encode(organisationRequestDto.getAdminPassword()));

        // Save the organisation entity to the database
        Organisation savedOrganisation = organisationRepository.save(organisation);


        // Map the saved entity to the response DTO
        return modelMapper.map(savedOrganisation, OrganisationResponseDto.class);
    } catch (Exception e) {
            System.out.println(e);
        throw new RuntimeException("Error creating organisation", e);
    }
}

}