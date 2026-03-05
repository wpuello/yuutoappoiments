package com.yuutoap.Appoiments.model.parameters;

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
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private BigDecimal price;

    private Integer maxDoctors;

    private Integer maxUsers;

    private Integer maxAppointmentsPerMonth;

    private Boolean medicalRecordsModule;

    private Boolean active;
}
