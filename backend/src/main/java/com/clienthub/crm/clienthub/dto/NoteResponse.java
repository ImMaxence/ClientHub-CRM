package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class NoteResponse {
    private Long id;
    private String content;
    private Long authorId;
    private Long companyId;
    private Long dealId;
    private java.time.LocalDateTime createdAt;
}
