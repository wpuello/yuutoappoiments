package com.yuutoap.Appoiments.model.agreements;


import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "agreement_specialties",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id","agreement_id","specialty_id"})
        }
)
public class AgreementSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="tenant_id", nullable=false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name="agreement_id", nullable=false)
    private Agreement agreement;

    @ManyToOne
    @JoinColumn(name="specialty_id", nullable=false)
    private Specialty specialty;

    // Cuota moderadora Estimada proporcionada como opcional
    @Column(name="estimated_moderating_fee", precision = 10, scale = 2)
    private BigDecimal estimatedModeratingFee;

    private Boolean active;
}
