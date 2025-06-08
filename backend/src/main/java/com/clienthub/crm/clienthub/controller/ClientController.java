package com.clienthub.crm.clienthub.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.service.ClientService;

import jakarta.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Client> searchByName(@RequestParam("q") String q) {
        return clientService.searchByName(q);
    }

    @PostMapping
    public ResponseEntity<Client> createClientJson(@Valid @RequestBody Client client) {
        Client created = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping(path = "/{id}/avatar")
    public ResponseEntity<Client> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Client updated = clientService.oneShotUploadAvatar(id, file);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Client> updateClientJson(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        Client updated = clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(updated);
    }
}
