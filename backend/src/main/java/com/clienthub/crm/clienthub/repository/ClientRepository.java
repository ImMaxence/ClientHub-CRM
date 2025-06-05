package com.clienthub.crm.clienthub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienthub.crm.clienthub.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainingIgnoreCase(String fragment);
}
