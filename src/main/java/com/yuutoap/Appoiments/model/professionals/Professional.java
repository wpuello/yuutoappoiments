package com.yuutoap.Appoiments.model.professionals;

import com.yuutoap.Appoiments.model.consultingrooms.ConsultingRoom;
import com.yuutoap.Appoiments.model.parameters.Tenant;
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
@Table(
        name = "professionals",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_professional_tenant_identification",
                        columnNames = {"tenant_id", "identification"}
                )
        }
)
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "consultingroom_id", nullable = false)
    private ConsultingRoom consultingRoom;

    @Column(nullable = false)
    private String identification;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    private String licenseNumber;

    private String phone;

    private String bio;

    @ManyToMany
    @JoinTable(
            name = "professional_specialties",
            joinColumns = @JoinColumn(name = "professional_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties;
}
