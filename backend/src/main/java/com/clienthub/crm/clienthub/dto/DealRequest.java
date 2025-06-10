package com.clienthub.crm.clienthub.dto;

import lombok.Data;

@Data
public class DealRequest {
    private String title;
    private java.math.BigDecimal value;
    private Integer probability;
    private String stage;
    private java.time.LocalDate expectedCloseDate;
    private Long companyId;
    private Long assignedTo;
    private String description;
}
