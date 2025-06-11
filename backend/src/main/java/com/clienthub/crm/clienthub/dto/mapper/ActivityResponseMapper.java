package com.clienthub.crm.clienthub.dto.mapper;

import com.clienthub.crm.clienthub.dto.ActivityResponse;
import com.clienthub.crm.clienthub.model.Activity;

public class ActivityResponseMapper {
    public static ActivityResponse toDto(Activity activity) {
        if (activity == null) return null;
        ActivityResponse dto = new ActivityResponse();
        dto.setId(activity.getId());
        dto.setTitle(activity.getTitle());
        dto.setDescription(activity.getDescription());
        dto.setType(activity.getType() != null ? activity.getType().name() : null);
        dto.setCreatedByUserId(activity.getCreatedByUserId());
        dto.setCompanyId(activity.getCompany() != null ? activity.getCompany().getId() : null);
        return dto;
    }
}
