package com.yuutoap.Appoiments.model.security;


import com.yuutoap.Appoiments.enums.AuthProvider;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.patients.Patient;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id","email"})
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    //Usuario Autenticados por Google
    @Enumerated(EnumType.STRING)
    private AuthProvider provider; //proveedor de login (LOCAL / GOOGLE)

    private String providerId; //id del usuario en Google
    private String profilePicture; //foto del perfil
    private Boolean emailVerified; //si el email fue verificado

    @Column(nullable = false)
    private String password;

    private String phone;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
