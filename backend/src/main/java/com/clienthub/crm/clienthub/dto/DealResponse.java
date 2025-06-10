package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class DealResponse {
    private Long id;
    private String title;
    private java.math.BigDecimal value;
    private Integer probability;
    private String stage;
    private java.time.LocalDate expectedCloseDate;
    private Long companyId;
    private Long assignedTo;
    private String description;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
