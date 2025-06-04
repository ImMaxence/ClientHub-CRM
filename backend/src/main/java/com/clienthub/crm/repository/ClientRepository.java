package com.clienthub.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clienthub.crm.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Tu pourras définir ici des méthodes de requête personnalisées au besoin.
}
