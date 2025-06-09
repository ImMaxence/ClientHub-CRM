package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Activity;
import com.clienthub.crm.clienthub.model.Activity.ActivityType;
import com.clienthub.crm.clienthub.service.ActivityService;
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
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        if (activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Activity>> getAllActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Activity> activities = activityService.getAllActivities(pageable);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Activity>> getActivitiesByCompany(@PathVariable Long companyId) {
        List<Activity> activities = activityService.getActivitiesByCompany(companyId);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<List<Activity>> getActivitiesByContact(@PathVariable Long contactId) {
        List<Activity> activities = activityService.getActivitiesByContact(contactId);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Activity>> getActivitiesByType(@PathVariable ActivityType type) {
        List<Activity> activities = activityService.getActivitiesByType(type);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activity>> getActivitiesByUser(@PathVariable Long userId) {
        List<Activity> activities = activityService.getActivitiesByUser(userId);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Activity>> getActivitiesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Activity> activities = activityService.getActivitiesByDateRange(startDate, endDate);
        return ResponseEntity.ok(activities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @Valid @RequestBody Activity activity) {
        Activity updatedActivity = activityService.updateActivity(id, activity);
        return ResponseEntity.ok(updatedActivity);
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