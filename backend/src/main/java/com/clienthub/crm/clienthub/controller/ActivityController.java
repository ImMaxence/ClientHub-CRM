package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Activity;
import com.clienthub.crm.clienthub.model.Activity.ActivityType;
import com.clienthub.crm.clienthub.service.ActivityService;
import com.clienthub.crm.clienthub.dto.ActivityRequest;
import com.clienthub.crm.clienthub.dto.ActivityResponse;
import com.clienthub.crm.clienthub.dto.mapper.ActivityResponseMapper;
import com.clienthub.crm.clienthub.dto.mapper.ActivityRequestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@Valid @RequestBody ActivityRequest activityRequest) {
        Activity createdActivity = activityService.createActivity(ActivityRequestMapper.toEntity(activityRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(ActivityResponseMapper.toDto(createdActivity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        if (activity != null) {
            return ResponseEntity.ok(ActivityResponseMapper.toDto(activity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ActivityResponse>> getAllActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Activity> activities = activityService.getAllActivities(pageable);
        return ResponseEntity.ok(activities.map(ActivityResponseMapper::toDto));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByCompany(@PathVariable Long companyId) {
        List<Activity> activities = activityService.getActivitiesByCompany(companyId);
        return ResponseEntity.ok(activities.stream().map(ActivityResponseMapper::toDto).toList());
    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByContact(@PathVariable Long contactId) {
        List<Activity> activities = activityService.getActivitiesByContact(contactId);
        return ResponseEntity.ok(activities.stream().map(ActivityResponseMapper::toDto).toList());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByType(@PathVariable ActivityType type) {
        List<Activity> activities = activityService.getActivitiesByType(type);
        return ResponseEntity.ok(activities.stream().map(ActivityResponseMapper::toDto).toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByUser(@PathVariable Long userId) {
        List<Activity> activities = activityService.getActivitiesByUser(userId);
        return ResponseEntity.ok(activities.stream().map(ActivityResponseMapper::toDto).toList());
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Activity> activities = activityService.getActivitiesByDateRange(startDate, endDate);
        return ResponseEntity.ok(activities.stream().map(ActivityResponseMapper::toDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Long id, @Valid @RequestBody ActivityRequest activityRequest) {
        Activity updatedActivity = activityService.updateActivity(id, ActivityRequestMapper.toEntity(activityRequest));
        return ResponseEntity.ok(ActivityResponseMapper.toDto(updatedActivity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/company/{companyId}")
    public ResponseEntity<Map<String, Object>> getCompanyActivityStats(@PathVariable Long companyId) {
        long count = activityService.countActivitiesByCompany(companyId);
        return ResponseEntity.ok(Map.of("companyId", companyId, "totalActivities", count));
    }

    @GetMapping("/stats/type/{type}")
    public ResponseEntity<Map<String, Object>> getTypeActivityStats(@PathVariable ActivityType type) {
        long count = activityService.countActivitiesByType(type);
        return ResponseEntity.ok(Map.of("type", type, "totalActivities", count));
    }

    @GetMapping("/types")
    public ResponseEntity<ActivityType[]> getActivityTypes() {
        return ResponseEntity.ok(ActivityType.values());
    }
}