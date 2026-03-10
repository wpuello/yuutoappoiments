package com.yuutoap.Appoiments.model.appointments;

import com.yuutoap.Appoiments.enums.RescheduleType;
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
        name = "appointment_reschedules",
        indexes = {
                @Index(name = "idx_ar_appointment", columnList = "appointment_id")
        }
)
public class AppointmentReschedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="tenant_id", nullable=false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name="appointment_id", nullable=false)
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    private RescheduleType type;

    @Column(name="reschedule_batch_id")
    private UUID rescheduleBatchId; //para agrupar citas con reprogramaciones masivas por parte del medico
    //Entonces cuando muevo 20 citas generas un batch id. y luego puedo filtrar por reschedule_batch_id y miro
    //esta reprogramacion masiva

    // horario anterior
    private LocalDateTime oldStartTime;
    private LocalDateTime oldEndTime;

    // horario nuevo
    private LocalDateTime newStartTime;
    private LocalDateTime newEndTime;

    @ManyToOne
    @JoinColumn(name="changed_by_user_id")
    private User changedBy;

    @Column(name="changed_at")
    private LocalDateTime changedAt;

    private String reason;

    @PrePersist
    public void prePersist(){
        this.changedAt = LocalDateTime.now();
    }

}
