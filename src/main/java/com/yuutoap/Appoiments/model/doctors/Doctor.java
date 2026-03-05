package com.yuutoap.Appoiments.model.doctors;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.security.User;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String licenseNumber;

    private String phone;

    private String bio;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialties",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;
}
