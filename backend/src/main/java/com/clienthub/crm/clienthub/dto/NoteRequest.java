package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class NoteRequest {
    private String content;
    private Long authorId;
    private Long companyId;
    private Long dealId;
}
