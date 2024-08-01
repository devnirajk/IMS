package com.InternalManagementSystem.IMS.dto.ClientDtos;

import com.InternalManagementSystem.IMS.common.Day;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class ClientDto {
        private String name;
        private String specialization;
        private String compounderName;
        private String email;
        private String address;
        private String city;
        private String state;
        private String compounderNumber;
        private String doctorNumber;
        private Set<Day> callDays;
        private LocalTime callTimingsStart;
        private LocalTime callTimingsEnd;
}

