package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.NoteRequest;
import com.clienthub.crm.clienthub.model.Note;
import com.clienthub.crm.clienthub.model.User;
import com.clienthub.crm.clienthub.model.Company;
import com.clienthub.crm.clienthub.model.Deal;

public class NoteRequestMapper {
    public static Note toEntity(NoteRequest dto) {
        if (dto == null) return null;
        Note entity = new Note();
        entity.setContent(dto.getContent());
        if (dto.getAuthorId() != null) {
            User author = new User();
            author.setId(dto.getAuthorId());
            entity.setAuthor(author);
        }
        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }
        if (dto.getDealId() != null) {
            Deal deal = new Deal();
            deal.setId(dto.getDealId());
            entity.setDeal(deal);
        }
        return entity;
    }
}
