package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class ActivityResponse {
    private Long id;
    private String title;
    private String description;
    private String type;
    private java.time.LocalDateTime date;
    private Integer duration;
    private Long companyId;
    private Long contactId;
    private Long createdByUserId;
    private java.time.LocalDateTime createdAt;
}
