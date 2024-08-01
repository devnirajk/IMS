package com.InternalManagementSystem.IMS.service.serviceImplementation;

import com.InternalManagementSystem.IMS.dto.ClientDtos.ClientDto;
import com.InternalManagementSystem.IMS.entity.Client;
import com.InternalManagementSystem.IMS.repository.ClientRepository;
import com.InternalManagementSystem.IMS.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientDto createClient(ClientDto clientDTO) {
        try {
            Client client = modelMapper.map(clientDTO, Client.class);
            Client savedClient = clientRepository.save(client);
            return modelMapper.map(savedClient, ClientDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDto getClientById(Long id) {
        try {
            Optional<Client> savedClient = clientRepository.findById(id);
            if (savedClient.isPresent()) {
                return modelMapper.map(savedClient.get(), ClientDto.class);
            } else {
                return null; // or throw a custom NotFoundException
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClientDto> getAllClients() {
        try {
            List<Client> clients = clientRepository.findAll();
            return clients.stream()
                    .map(client -> modelMapper.map(client, ClientDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteClient(Long id) {
        try {
            Optional<Client> client = clientRepository.findById(id);
            if (client.isPresent()) {
                clientRepository.deleteById(id);
                return true;
            } else {
                return false; // or throw a custom NotFoundException
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
