package com.clienthub.crm.clienthub.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.clienthub.crm.clienthub.client.FastApiClient;
import com.clienthub.crm.clienthub.model.Client;
import com.clienthub.crm.clienthub.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FastApiClient fastApiClient;

    public ClientService(ClientRepository clientRepository,
            FastApiClient fastApiClient) {
        this.clientRepository = clientRepository;
        this.fastApiClient = fastApiClient;
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Client non trouvé"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client oneShotUploadAvatar(Long id, MultipartFile file) {
        Client c = getClientById(id);
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Fichier avatar manquant");
        }
        String avatarUrl = fastApiClient.uploadOneShot(file);
        c.setAvatarUrl(avatarUrl);
        return clientRepository.save(c);
    }

    public void deleteClient(Long id) {
        Client c = getClientById(id);
        clientRepository.delete(c);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client c = getClientById(id);
        c.setNom(clientDetails.getNom());
        c.setEmail(clientDetails.getEmail());
        return clientRepository.save(c);
    }

    public List<Client> searchByName(String fragment) {
        return clientRepository.findByNomContainingIgnoreCase(fragment);
    }
}
