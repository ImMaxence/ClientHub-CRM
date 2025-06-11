package com.clienthub.crm.clienthub.service;

import com.clienthub.crm.clienthub.dto.CompanyRequest;

import com.clienthub.crm.clienthub.model.Company;
import com.clienthub.crm.clienthub.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final com.clienthub.crm.clienthub.client.FastApiClient fastApiClient;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise non trouvée"));
    }

    public Company createCompany(Company company) {
        company.setName(company.getName());
        company.setAddress(company.getAddress());
        company.setWebsite(company.getWebsite());
        company.setAvatarUrl(company.getAvatarUrl());
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company company) {
        Company existingCompany = getCompanyById(id);
        existingCompany.setName(company.getName());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setWebsite(company.getWebsite());
        // avatarUrl inchangé
        return companyRepository.save(existingCompany);
    }

    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }

    /**
     * Upload d'un avatar pour une entreprise.
     */
    public Company oneShotUploadAvatar(Long id, org.springframework.web.multipart.MultipartFile file) {
        Company company = getCompanyById(id);
        if (file == null || file.isEmpty()) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST, "Fichier avatar manquant");
        }
        String avatarUrl = fastApiClient.uploadOneShot(file);
        company.setAvatarUrl(avatarUrl);
        return companyRepository.save(company);
    }
}
