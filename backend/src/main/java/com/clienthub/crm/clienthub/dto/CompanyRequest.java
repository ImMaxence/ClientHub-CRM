package com.clienthub.crm.clienthub.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyRequest {
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
}
