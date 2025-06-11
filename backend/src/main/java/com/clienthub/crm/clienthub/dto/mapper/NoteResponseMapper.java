package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.NoteResponse;
import com.clienthub.crm.clienthub.model.Note;

public class NoteResponseMapper {
    public static NoteResponse toDto(Note note) {
        if (note == null) return null;
        NoteResponse dto = new NoteResponse();
        dto.setId(note.getId());
        dto.setContent(note.getContent());
        dto.setAuthorId(note.getAuthor() != null ? note.getAuthor().getId() : null);
        dto.setDealId(note.getDeal() != null ? note.getDeal().getId() : null);
        return dto;
    }
}
