package com.clienthub.crm.clienthub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deals")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre du deal est obligatoire")
    @Size(max = 255, message = "Le titre ne doit pas dépasser 255 caractères")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "La valeur est obligatoire")
    @DecimalMin(value = "0.0", message = "La valeur doit être positive")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal value;

    @Min(value = 0, message = "La probabilité doit être entre 0 et 100")
    @Max(value = 100, message = "La probabilité doit être entre 0 et 100")
    @Column(nullable = false)
    private Integer probability = 50;

    @NotNull(message = "L'étape est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DealStage stage = DealStage.LEAD;

    @Future(message = "La date de closing doit être dans le futur")
    private LocalDate expectedCloseDate;

    @NotNull(message = "L'entreprise est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "assigned_to")
    private Long assignedTo; // ID de l'utilisateur assigné

    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    @Column(columnDefinition = "TEXT")
    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Enum
    public enum DealStage {
        LEAD, QUALIFIED, PROPOSAL, NEGOTIATION, WON, LOST
    }
}