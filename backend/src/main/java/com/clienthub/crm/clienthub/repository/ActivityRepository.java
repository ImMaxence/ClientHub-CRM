package com.clienthub.crm.clienthub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clienthub.crm.clienthub.model.Activity;
import com.clienthub.crm.clienthub.model.Activity.ActivityType;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByCompanyIdOrderByDateDesc(Long companyId);

    List<Activity> findByContactIdOrderByDateDesc(Long contactId);

    List<Activity> findByTypeOrderByDateDesc(ActivityType type);

    List<Activity> findByCreatedByUserIdOrderByDateDesc(Long userId);

    List<Activity> findByDateBetweenOrderByDateDesc(LocalDateTime startDate, LocalDateTime endDate);

    long countByCompanyId(Long companyId);

    long countByType(ActivityType type);

    @Query("SELECT a FROM Activity a WHERE a.company.id = :companyId AND a.type = :type ORDER BY a.date DESC")
    List<Activity> findByCompanyIdAndTypeOrderByDateDesc(@Param("companyId") Long companyId,
            @Param("type") ActivityType type);

    @Query("SELECT a FROM Activity a WHERE a.contact.id = :contactId AND a.type = :type ORDER BY a.date DESC")
    List<Activity> findByContactIdAndTypeOrderByDateDesc(@Param("contactId") Long contactId,
            @Param("type") ActivityType type);

    @Query("SELECT a FROM Activity a WHERE a.date >= :startDate ORDER BY a.date DESC")
    List<Activity> findRecentActivities(@Param("startDate") LocalDateTime startDate);
}
