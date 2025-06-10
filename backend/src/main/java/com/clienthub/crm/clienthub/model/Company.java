package com.clienthub.crm.clienthub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères")
    @Column(nullable = false)
    private String name;

    @Size(max = 100, message = "Le secteur ne doit pas dépasser 100 caractères")
    private String industry;

    @Enumerated(EnumType.STRING)
    private CompanySize size;

    @Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}.*$", message = "Le format du site web n'est pas valide")
    private String website;

    @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
    private String address;

    @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
    private String city;

    @Size(max = 100, message = "Le pays ne doit pas dépasser 100 caractères")
    private String country;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    @Column(name = "assigned_to")
    private Long assignedTo; // ID de l'utilisateur assigné

    @DecimalMin(value = "0.0", message = "Le chiffre d'affaires doit être positif")
    private BigDecimal revenue;

    @Size(max = 1000, message = "Les notes ne doivent pas dépasser 1000 caractères")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    private String avatarUrl;

    public enum CompanySize {
        SMALL, MEDIUM, LARGE
    }

    public enum CompanyStatus {
        PROSPECT, CLIENT, INACTIVE
    }
}