package com.InternalManagementSystem.IMS.service;

import com.InternalManagementSystem.IMS.dto.ClientDtos.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDTO);
    ClientDto getClientById(Long id);
    List<ClientDto> getAllClients();
    boolean deleteClient(Long id);
}
