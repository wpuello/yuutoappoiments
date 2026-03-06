package com.yuutoap.Appoiments.model.convenios;

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
        name = "convenios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id","name"})
        }
)
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    private String code;

    private String nit;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private String city;

    private String country;

    private String observations;

    private Boolean active;

}
