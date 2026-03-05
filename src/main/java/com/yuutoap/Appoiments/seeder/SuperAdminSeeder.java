package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.enums.AuthProvider;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.security.Role;
import com.yuutoap.Appoiments.model.security.User;
import com.yuutoap.Appoiments.service.parameters.contract.TenantService;
import com.yuutoap.Appoiments.service.security.contract.RoleService;
import com.yuutoap.Appoiments.service.security.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(4) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class SuperAdminSeeder implements CommandLineRunner{

    private final UserService userService;
    private final TenantService tenantService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userService.findByEmail("admin@yuuto.com").isPresent()) {
            return;
        }

        Tenant tenant = tenantService.findBySlug("yuuto-system")
                .orElseThrow(() -> new RuntimeException("Tenant yuuto-system not found"));

        Role role = roleService.findByName("SUPER_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role SUPER_ADMIN not found"));

        User user = User.builder()
                .tenant(tenant)
                .firstName("Super")
                .lastName("Admin")
                .email("admin@yuuto.com")
                .provider(AuthProvider.LOCAL)
                .emailVerified(true)
                .password(passwordEncoder.encode("Elles2012*.*"))
                .phone("3000000000")
                .active(true)
                .roles(Set.of(role))
                .build();

        userService.save(user);

        System.out.println("Super Admin creado correctamente");
    }
}
