package com.clienthub.crm.clienthub.service;

import com.clienthub.crm.clienthub.model.Activity;
import com.clienthub.crm.clienthub.model.Activity.ActivityType;
import com.clienthub.crm.clienthub.repository.ActivityRepository;
import com.clienthub.crm.clienthub.repository.CompanyRepository;
import com.clienthub.crm.clienthub.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    public Activity createActivity(Activity activity) {
        // Vérifier que l'entreprise existe
        if (!companyRepository.existsById(activity.getCompany().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'entreprise avec l'ID " + activity.getCompany().getId() + " n'existe pas");
        }

        // Vérifier que le contact existe (si spécifié)
        if (activity.getContact() != null &&
                !contactRepository.existsById(activity.getContact().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Le contact avec l'ID " + activity.getContact().getId() + " n'existe pas");
        }

        return activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Activité non trouvé"));
    }

    public Page<Activity> getAllActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    public List<Activity> getActivitiesByCompany(Long companyId) {
        return activityRepository.findByCompanyIdOrderByDateDesc(companyId);
    }

    public List<Activity> getActivitiesByContact(Long contactId) {
        return activityRepository.findByContactIdOrderByDateDesc(contactId);
    }

    public List<Activity> getActivitiesByType(ActivityType type) {
        return activityRepository.findByTypeOrderByDateDesc(type);
    }

    public List<Activity> getActivitiesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return activityRepository.findByDateBetweenOrderByDateDesc(startDate, endDate);
    }

    public List<Activity> getActivitiesByUser(Long userId) {
        return activityRepository.findByCreatedByUserIdOrderByDateDesc(userId);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activity.setType(updatedActivity.getType());
                    activity.setTitle(updatedActivity.getTitle());
                    activity.setDescription(updatedActivity.getDescription());
                    activity.setDate(updatedActivity.getDate());
                    activity.setDuration(updatedActivity.getDuration());

                    // Vérifier l'entreprise si elle a changé
                    if (!activity.getCompany().getId().equals(updatedActivity.getCompany().getId())) {
                        if (!companyRepository.existsById(updatedActivity.getCompany().getId())) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "L'entreprise avec l'ID " + updatedActivity.getCompany().getId() + " n'existe pas");
                        }
                        activity.setCompany(updatedActivity.getCompany());
                    }

                    // Vérifier le contact si il a changé
                    if (updatedActivity.getContact() != null) {
                        if (!contactRepository.existsById(updatedActivity.getContact().getId())) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "Le contact avec l'ID " + updatedActivity.getContact().getId() + " n'existe pas");
                        }
                        activity.setContact(updatedActivity.getContact());
                    } else {
                        activity.setContact(null);
                    }

                    return activityRepository.save(activity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Activité non trouvée avec l'ID: " + id));
    }

    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Activité non trouvée avec l'ID: " + id);
        }
        activityRepository.deleteById(id);
    }

    public long countActivitiesByCompany(Long companyId) {
        return activityRepository.countByCompanyId(companyId);
    }

    public long countActivitiesByType(ActivityType type) {
        return activityRepository.countByType(type);
    }
}