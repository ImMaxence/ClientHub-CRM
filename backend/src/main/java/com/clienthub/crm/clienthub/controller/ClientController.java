package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.service.ClientService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public Page<Client> getAllClients(Pageable pageable) {
        return clientService.getAllClients(pageable);
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
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

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetail) {
        return clientService.updateClient(id, clientDetail);
    }

    @GetMapping("/search")
    public List<Client> getByName(@RequestParam String q) {
        return clientService.searchByName(q);
    }
}
