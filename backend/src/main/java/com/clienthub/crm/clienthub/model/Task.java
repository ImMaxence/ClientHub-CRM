package com.clienthub.crm.clienthub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre de la tâche est obligatoire")
    @Size(max = 255, message = "Le titre ne doit pas dépasser 255 caractères")
    @Column(nullable = false)
    private String title;

    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "La priorité est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;

    @Future(message = "La date d'échéance doit être dans le futur")
    private LocalDateTime dueDate;

    @NotNull(message = "L'assignation est obligatoire")
    @Column(name = "assigned_to", nullable = false)
    private Long assignedTo; // ID de l'utilisateur assigné

    // Relation polymorphe - soit vers Company soit vers Deal
    @Column(name = "related_entity_type")
    @Enumerated(EnumType.STRING)
    private RelatedEntityType relatedEntityType;

    @Column(name = "related_entity_id")
    private Long relatedEntityId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Enums
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public enum TaskStatus {
        TODO, IN_PROGRESS, DONE
    }

    public enum RelatedEntityType {
        COMPANY, DEAL
    }
}