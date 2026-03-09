package com.yuutoap.Appoiments.model.specialties;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name="specialty_parameters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id","specialty_id"})
        }
)
public class SpecialtyParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name="specialty_id")
    private Specialty specialty;

    // Tiempo Estipulado por cada Cita
    @Column(name="appointment_duration_minutes",nullable = false)
    private Integer appointmentDurationMinutes;


    // Tiempo del medico entre pacientes
    @Column(name="buffer_between_appointments")
    private Integer bufferBetweenAppointments;

    //Duración de la cita en minutos para Autoprorgamacion
    //Si el médico trabaja de 8:00 a 10:00 y el slotIntervalMinutes es 15,
    //el sistema genera automáticamente estos slots cada 15 minutos.
    @Column(name = "slot_interval_minutes") //Opcional puede quedar vacio
    private Integer slotIntervalMinutes;
}
