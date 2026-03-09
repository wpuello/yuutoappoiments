package com.yuutoap.Appoiments.model.parameters;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tenant_parameters")
public class TenantParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = false, unique = true)
    private Tenant tenant;

    // Notificaciones
    @Column(name = "send_email_notifications")
    private Boolean sendEmailNotifications;

    @Column(name = "email_notifications",nullable = false)
    private String emailNotifications;

    @Column(name = "send_whatsapp_notifications")
    private Boolean sendWhatsappNotifications;

    @Column(name = "whatsapp_number_notifications",nullable = false, length = 20)
    private String whatsappNumberNotifications;

    // Recordatorio antes de la cita 3 horas Antes
    @Column(name = "reminder_hours_before")
    private Integer reminderHoursBefore;

    // Tiempo Maximo en horas para cancelar la Cita
    @Column(name = "cancelation_hours_before")
    private Integer cancelationHoursBefore;

    // Si se permite agendar citas online
    @Column(name = "allow_online_booking")
    private Boolean allowOnlineBooking;

}
