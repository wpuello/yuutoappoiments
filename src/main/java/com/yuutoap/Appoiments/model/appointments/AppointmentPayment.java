package com.yuutoap.Appoiments.model.appointments;

import com.yuutoap.Appoiments.enums.PaymentMethod;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.security.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "appointment_payments",
        indexes = {
                @Index(name = "idx_payment_appointment", columnList = "appointment_id")
        }
)
public class AppointmentPayment {

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
    @Column(name="payment_method", nullable=false)
    private PaymentMethod paymentMethod;

    @Column(name="amount", precision = 10, scale = 2, nullable=false)
    private BigDecimal amount;

    @Column(name="transaction_reference")
    private String transactionReference;

    @Column(name="payment_date")
    private LocalDateTime paymentDate;

    private String notes;

    // Usuario que registró el pago
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
