package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.ActivityRequest;
import com.clienthub.crm.clienthub.model.Activity;
import com.clienthub.crm.clienthub.model.Company;
import com.clienthub.crm.clienthub.model.Contact;

public class ActivityRequestMapper {
    public static Activity toEntity(ActivityRequest dto) {
        if (dto == null) return null;
        Activity entity = new Activity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        if (dto.getType() != null) {
            try {
                entity.setType(Activity.ActivityType.valueOf(dto.getType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setType(Activity.ActivityType.NOTE); // Valeur par défaut
            }
        }
        entity.setDate(dto.getDate());
        entity.setDuration(dto.getDuration());
        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }
        if (dto.getContactId() != null) {
            Contact contact = new Contact();
            contact.setId(dto.getContactId());
            entity.setContact(contact);
        }
        entity.setCreatedByUserId(dto.getCreatedByUserId());
        return entity;
    }
}
