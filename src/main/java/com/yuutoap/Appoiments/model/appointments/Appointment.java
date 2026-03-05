package com.yuutoap.Appoiments.model.appointments;


import com.yuutoap.Appoiments.enums.AppointmentStatus;
import com.yuutoap.Appoiments.model.doctors.Doctor;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.patients.Patient;
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
@Table(
        name = "appointments",
        indexes = {
                @Index(name = "idx_doctor_start", columnList = "doctor_id,start_time")
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
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

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
