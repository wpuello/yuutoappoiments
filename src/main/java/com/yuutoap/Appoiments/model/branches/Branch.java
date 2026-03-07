package com.yuutoap.Appoiments.model.branches;


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
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    private String code;

    private String address;

    private String city;

    private String phone;

    private String email;

    private Boolean active;
}
