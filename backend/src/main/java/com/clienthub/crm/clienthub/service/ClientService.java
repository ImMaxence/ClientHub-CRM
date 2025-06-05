package com.clienthub.crm.clienthub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client n'a pas été trouvé"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }

    public Client updateClient(Long id, Client clientDetail) {
        Client client = getClientById(id);
        client.setNom(clientDetail.getNom());
        client.setEmail(clientDetail.getEmail());
        return clientRepository.save(client);
    }

    public List<Client> searchByName(String fragment) {
        return clientRepository.findByNomContainingIgnoreCase(fragment);
    }

}
