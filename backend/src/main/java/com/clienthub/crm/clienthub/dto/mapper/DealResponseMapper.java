package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.DealResponse;
import com.clienthub.crm.clienthub.model.Deal;

public class DealResponseMapper {
    public static DealResponse toDto(Deal deal) {
        if (deal == null) return null;
        DealResponse dto = new DealResponse();
        dto.setId(deal.getId());
        dto.setTitle(deal.getTitle());
        dto.setDescription(deal.getDescription());
        dto.setValue(deal.getValue());
        dto.setCompanyId(deal.getCompany() != null ? deal.getCompany().getId() : null);
        dto.setAssignedTo(deal.getAssignedTo());
        return dto;
    }
}
