package com.InternalManagementSystem.IMS.entity;

import com.InternalManagementSystem.IMS.common.ClientStatus;
import com.InternalManagementSystem.IMS.common.Day;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
public class Client extends BaseModel {
    private String name;
    private String specialization;
    private String compounderName;
    private String email;
    private String address;
    private String city;
    private String state;

    private Long pinCode;

    private String compounderNumber;
    private String doctorNumber;

    @ElementCollection  // annotation indicates that the field is collection of basic types or embeddable objects
    @CollectionTable(name = "client_call_days", joinColumns = @JoinColumn(name = "client_id")) // details of collection table
    @Column(name = "call_day") // This column will be created named (call_day) in the table client_call_days
    private Set<Day> callDays;

    private LocalTime callTimingsStart;
    private LocalTime callTimingsEnd;


    private ClientStatus status;
}
