package com.yuutoap.Appoiments.subscription;

import com.yuutoap.Appoiments.enums.BillingCycle;
import com.yuutoap.Appoiments.enums.SubscriptionStatus;
import com.yuutoap.Appoiments.model.parameters.Plan;
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
        name = "subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id"})
        }
)
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 1 tenant = 1 subscription (modelo simple y robusto)
    @OneToOne(optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "billing_cycle", nullable = false)
    private BillingCycle billingCycle;

    // Fin del período actual (cuándo vence lo pagado / activo)
    @Column(name = "current_period_end")
    private LocalDateTime currentPeriodEnd;

    // Fin del trial (si aplica)
    @Column(name = "trial_end")
    private LocalDateTime trialEnd;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) this.status = SubscriptionStatus.TRIALING;
        if (this.billingCycle == null) this.billingCycle = BillingCycle.MONTHLY;
    }
}
