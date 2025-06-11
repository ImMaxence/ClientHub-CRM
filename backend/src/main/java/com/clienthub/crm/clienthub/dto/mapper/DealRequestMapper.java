package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.DealRequest;
import com.clienthub.crm.clienthub.model.Deal;

public class DealRequestMapper {
    public static Deal toEntity(DealRequest dto) {
        if (dto == null) return null;
        Deal entity = new Deal();
        entity.setTitle(dto.getTitle());
        entity.setValue(dto.getValue());
        entity.setProbability(dto.getProbability());
        // Conversion String vers Enum pour le stage
        if (dto.getStage() != null) {
            try {
                entity.setStage(Deal.DealStage.valueOf(dto.getStage().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStage(Deal.DealStage.LEAD); // Valeur par défaut
            }
        }
        entity.setExpectedCloseDate(dto.getExpectedCloseDate());
        // Instanciation de Company à partir de l'ID
        if (dto.getCompanyId() != null) {
            com.clienthub.crm.clienthub.model.Company company = new com.clienthub.crm.clienthub.model.Company();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
