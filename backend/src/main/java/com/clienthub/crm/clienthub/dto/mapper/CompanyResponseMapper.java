package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.CompanyResponse;
import com.clienthub.crm.clienthub.model.Company;

public class CompanyResponseMapper {
    public static CompanyResponse toDto(Company company) {
        if (company == null) return null;
        CompanyResponse dto = new CompanyResponse();
        dto.setId(company.getId());
        dto.setName(company.getName());
        
        dto.setAddress(company.getAddress());
        
        dto.setWebsite(company.getWebsite());
        dto.setAvatarUrl(company.getAvatarUrl());
        return dto;
    }
}
