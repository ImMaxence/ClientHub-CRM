package com.clienthub.crm.clienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienthub.crm.clienthub.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
