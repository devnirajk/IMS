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

    String gstIN;
    String pan;
    String businessEmail;
    String businessPhone;
    String adminEmail;
    String adminPassword;
    String officeAddress;
}