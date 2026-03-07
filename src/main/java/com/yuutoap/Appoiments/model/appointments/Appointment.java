package com.yuutoap.Appoiments.model.appointments;


import com.yuutoap.Appoiments.enums.AppointmentStatus;
import com.yuutoap.Appoiments.model.agreements.Agreement;
import com.yuutoap.Appoiments.model.professionals.Professional;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.patients.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "appointments",
        indexes = {
                @Index(name = "idx_professional_start", columnList = "professional_id,start_time")
        }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @Column(name = "appoimentDate", nullable = false)
    private LocalDate appoimentDate;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "moderating_fee", precision = 10, scale = 2)
    private BigDecimal moderatingFee;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_by_phone")
    private Boolean contactByPhone;

    @Column(name = "contact_by_whatsapp")
    private Boolean contactByWhatsapp;

    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {

        this.createdAt = LocalDateTime.now();

        if(this.status == null){
            this.status = AppointmentStatus.PROGRAMADA;
        }

    }
}
