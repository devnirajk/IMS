package com.InternalManagementSystem.IMS.controller;

import com.InternalManagementSystem.IMS.dto.ClientDtos.ClientDto;
import com.InternalManagementSystem.IMS.service.ClientService;
import com.InternalManagementSystem.IMS.util.ResponseHandler;
import com.InternalManagementSystem.IMS.util.ErrorResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientDto clientDto) {
        try {
            ClientDto createdClient = clientService.createClient(clientDto);
            return ResponseHandler.generateResponse("Client created successfully", HttpStatus.CREATED, createdClient);
        } catch (Exception e) {
            return ErrorResponseHandler.generateErrorResponse("Failed to create client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        try {
            ClientDto clientDto = clientService.getClientById(id);
            if (clientDto != null) {
                return ResponseHandler.generateResponse("Client found", HttpStatus.OK, clientDto);
            } else {
                return ErrorResponseHandler.generateErrorResponse("Client not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ErrorResponseHandler.generateErrorResponse("Failed to retrieve client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllClients() {
        try {
            List<ClientDto> clients = clientService.getAllClients();
            return ResponseHandler.generateResponse("Clients retrieved successfully", HttpStatus.OK, clients);
        } catch (Exception e) {
            return ErrorResponseHandler.generateErrorResponse("Failed to retrieve clients", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        try {
            boolean isDeleted = clientService.deleteClient(id);
            if (isDeleted) {
                return ResponseHandler.generateResponse("Client deleted successfully", HttpStatus.NO_CONTENT, null);
            } else {
                return ErrorResponseHandler.generateErrorResponse("Client not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ErrorResponseHandler.generateErrorResponse("Failed to delete client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}