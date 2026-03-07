package com.yuutoap.Appoiments.model.parameters;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tenants") //Company Data Name
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nit;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String legalRepresentativeName;

    @Column(nullable = false)
    private String legalRepresentativeId;

    @Column(nullable = false, unique = true)
    private String slug; //Para Identificar El Tenant

    @Column(nullable = false)
    private String email;

    private String phone;

    private String address;

    private String city;

    private String country;

    @Column(name = "logo_url")
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    private String status;

    private String observations;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
