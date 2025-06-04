package com.clienthub.crm.clienthub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.repository.ClientRepository;
import com.clienthub.crm.clienthub.exception.ResourceNotFoundException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le client n'a pas été trouvé"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient2(Long id) {
        clientRepository.deleteById(id);
    }

    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }

}
