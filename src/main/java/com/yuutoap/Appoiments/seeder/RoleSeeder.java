package com.yuutoap.Appoiments.seeder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.yuutoap.Appoiments.model.security.Role;
import com.yuutoap.Appoiments.service.security.contract.RoleService;
import org.springframework.core.annotation.Order;
import java.util.List;

@Component
@Order(3) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    public void run(String... args) {

        if (!roleService.findAll().isEmpty()) {
            return;
        }

        List<Role> roles = List.of(

                Role.builder()
                        .name("SUPER_ADMIN")
                        .description("Administra toda la plataforma")
                        .active(true)
                        .build(),

                Role.builder()
                        .name("TENANT_ADMIN")
                        .description("Administrador del consultorio")
                        .active(true)
                        .build(),

                Role.builder()
                        .name("DOCTOR")
                        .description("Médico del consultorio")
                        .active(true)
                        .build(),

                Role.builder()
                        .name("RECEPTIONIST")
                        .description("Encargado de agendar citas")
                        .active(true)
                        .build(),

                Role.builder()
                        .name("PATIENT")
                        .description("Paciente del sistema")
                        .active(true)
                        .build()
        );

        roleService.saveAll(roles);

        System.out.println("Roles creados correctamente");
    }
}
