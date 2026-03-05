package com.yuutoap.Appoiments.model.appointments;

import com.yuutoap.Appoiments.enums.NotificationStatus;
import com.yuutoap.Appoiments.enums.NotificationType;
import com.yuutoap.Appoiments.model.parameters.Tenant;
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
        name = "notifications",
        indexes = {
                @Index(name = "idx_notif_tenant", columnList = "tenant_id"),
                @Index(name = "idx_notif_appointment", columnList = "appointment_id"),
                @Index(name = "idx_notif_status", columnList = "status")
        }
)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment; // puede ser null si es otra notificación general

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    // A quién se le envía (email, phone, token push, etc.)
    @Column(name = "recipient", nullable = false)
    private String recipient;

    // Mensaje o payload (simple por ahora)
    @Column(columnDefinition = "text")
    private String message;

    // Para programar recordatorios: ej 24h antes
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(columnDefinition = "text")
    private String error;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = NotificationStatus.PENDING;
    }
}
