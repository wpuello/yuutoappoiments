package com.yuutoap.Appoiments.model.appointments;


import com.yuutoap.Appoiments.enums.AppointmentStatus;
import com.yuutoap.Appoiments.model.agreements.Agreement;
import com.yuutoap.Appoiments.model.professionals.Professional;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.patients.Patient;
import com.yuutoap.Appoiments.model.specialties.Specialty;
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
                @Index(name = "idx_professional_start", columnList = "professional_id,start_time"),
                @Index(name = "idx_authorization_number", columnList = "authorization_number"),
                @Index(name = "idx_patient", columnList = "patient_id"),
                @Index(name = "idx_agreement_date", columnList = "agreement_id,appointment_date"),
                @Index(name = "idx_specialty_date", columnList = "specialty_id,appointment_date")
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
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @ManyToOne
    @JoinColumn(name="specialty_id", nullable=false)
    private Specialty specialty;

    @Column(name = "authorization_number", length = 50)
    private String authorizationNumber;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "moderating_fee", precision = 10, scale = 2, nullable = true)
    private BigDecimal moderatingFee;

    @Column(name = "contact_phone")
    private String contactPhone;

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
