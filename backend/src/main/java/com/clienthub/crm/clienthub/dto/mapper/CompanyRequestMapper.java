package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.CompanyRequest;
import com.clienthub.crm.clienthub.model.Company;

public class CompanyRequestMapper {
    public static Company toEntity(CompanyRequest dto) {
        if (dto == null) return null;
        Company entity = new Company();
        entity.setName(dto.getName());
        entity.setIndustry(dto.getIndustry());
        if (dto.getSize() != null) {
            try {
                entity.setSize(Company.CompanySize.valueOf(dto.getSize().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setSize(Company.CompanySize.SMALL);
            }
        }
        entity.setWebsite(dto.getWebsite());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        if (dto.getStatus() != null) {
            try {
                entity.setStatus(Company.CompanyStatus.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStatus(Company.CompanyStatus.PROSPECT);
            }
        }
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setRevenue(dto.getRevenue());
        entity.setNotes(dto.getNotes());
        entity.setAvatarUrl(dto.getAvatarUrl());
        return entity;
    }
}
