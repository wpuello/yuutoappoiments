package com.yuutoap.Appoiments.model.patients;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.security.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;

    private String lastName;

    private String documentNumber;

    private LocalDate birthDate;

    private String phone;

    private String address;
}
