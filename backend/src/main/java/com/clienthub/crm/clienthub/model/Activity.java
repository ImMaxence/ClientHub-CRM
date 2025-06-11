package com.clienthub.crm.clienthub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le type d'activité est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType type;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre ne doit pas dépasser 255 caractères")
    @Column(nullable = false)
    private String title;

    @Size(max = 2000, message = "La description ne doit pas dépasser 2000 caractères")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "La date est obligatoire")
    @Column(nullable = false)
    private LocalDateTime date;

    @Min(value = 0, message = "La durée doit être positive")
    private Integer duration; // en minutes

    @NotNull(message = "L'entreprise est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact; // Optionnel

    @NotNull(message = "Le créateur est obligatoire")
    @Column(name = "created_by_user_id", nullable = false)
    private Long createdByUserId; // ID de l'utilisateur qui a créé l'activité

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Enum
    public enum ActivityType {
        CALL, EMAIL, MEETING, NOTE
    }
}