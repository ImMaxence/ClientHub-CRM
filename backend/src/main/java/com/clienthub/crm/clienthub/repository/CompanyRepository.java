package com.clienthub.crm.clienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienthub.crm.clienthub.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
