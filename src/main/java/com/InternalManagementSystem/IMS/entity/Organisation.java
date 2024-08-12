package com.InternalManagementSystem.IMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation extends BaseModel{
    String companyName;

    @Column(length = 2048)
    String companyLogoURL;

    @Column(unique = true)
    String gstIN;

    @Column(unique=true)
    String pan;


    String businessEmail;
    String businessPhone;

    @Column(unique=true)
    String adminEmail;

    String adminPassword;
    String officeAddress;
    String city;
    String state;
    String pincode;
}