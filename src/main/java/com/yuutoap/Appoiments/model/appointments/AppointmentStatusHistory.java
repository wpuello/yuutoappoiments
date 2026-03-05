package com.yuutoap.Appoiments.model.appointments;

import com.yuutoap.Appoiments.enums.AppointmentStatus;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.security.User;
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
        name = "appointment_status_history",
        indexes = {
                @Index(name = "idx_ash_appointment", columnList = "appointment_id"),
                @Index(name = "idx_ash_tenant", columnList = "tenant_id")
        }
)
public class AppointmentStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "changed_by_user_id")
    private User changedBy; // puede ser null si el cambio fue automático

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    private String notes; // opcional: motivo del cambio

    @PrePersist
    public void prePersist() {
        this.changedAt = LocalDateTime.now();
    }
}
