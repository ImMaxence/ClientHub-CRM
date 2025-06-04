package com.clienthub.crm.controller;

import com.clienthub.crm.model.Client;
import com.clienthub.crm.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    // Le constructeur permet l'injection de dépendance du repository
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Endpoint GET pour récupérer la liste de tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Endpoint POST pour ajouter un nouveau client à la base de données
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Par la suite, on pourra ajouter d’autres endpoints pour modifier ou supprimer
    // un client
}
