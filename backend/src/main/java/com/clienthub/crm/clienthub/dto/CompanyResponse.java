package com.clienthub.crm.clienthub.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CompanyResponse {
    private Long id;
    private String name;
    private String industry;
    private String size;
    private String website;
    private String address;
    private String city;
    private String country;
    private String status;
    private Long assignedTo;
    private java.math.BigDecimal revenue;
    private String notes;
    private String avatarUrl;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private String createdBy;
    private String lastModifiedBy;
}
