package com.yuutoap.Appoiments.model.consultingrooms;

import com.yuutoap.Appoiments.model.branches.Branch;
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
@Table(name = "consultingrooms")
public class ConsultingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(nullable = false)
    private String name;

    private String code;

    private String floor;

    private String building;

    private String description;

    private Boolean active;

}
