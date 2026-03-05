package com.yuutoap.Appoiments.model.security;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {

    //SUPER_ADMIN administra toda la plataforma
    //TENANT_ADMIN administrador del consultorio
    //DOCTOR médico
    //RECEPTIONIST agenda citas
    //PATIENT paciente

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private Boolean active;
}
