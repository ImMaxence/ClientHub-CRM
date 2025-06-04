package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.service.ClientService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
