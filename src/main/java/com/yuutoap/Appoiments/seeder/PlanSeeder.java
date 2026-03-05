package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.model.parameters.Plan;
import com.yuutoap.Appoiments.service.parameters.contract.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@Order(1) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class PlanSeeder implements CommandLineRunner {


    @Autowired
    private final PlanService planService;

    @Override
    public void run(String... args) {

        // Si ya hay planes, no hace nada
        if (!planService.findAll().isEmpty()) {
            return;
        }

        List<Plan> plans = List.of(
                Plan.builder()
                        .name("BASICO")
                        .price(new BigDecimal("120000"))
                        .maxDoctors(1)
                        .maxUsers(2)
                        .maxAppointmentsPerMonth(200)
                        .medicalRecordsModule(false)
                        .active(true)
                        .build(),

                Plan.builder()
                        .name("BASICO-PROFESSIONAL")
                        .price(new BigDecimal("160000"))
                        .maxDoctors(1)
                        .maxUsers(2)
                        .maxAppointmentsPerMonth(2500)
                        .medicalRecordsModule(true)
                        .active(true)
                        .build(),

                Plan.builder()
                        .name("CLINICA")
                        .price(new BigDecimal("360000"))
                        .maxDoctors(15)
                        .maxUsers(50)
                        .maxAppointmentsPerMonth(5000)
                        .medicalRecordsModule(true)
                        .active(true)
                        .build(),

                Plan.builder()
                        .name("HOSPITAL")
                        .price(new BigDecimal("750000"))
                        .maxDoctors(50)
                        .maxUsers(200)
                        .maxAppointmentsPerMonth(20000)
                        .medicalRecordsModule(true)
                        .active(true)
                        .build(),

                Plan.builder()
                        .name("ADMINISTRADOR")
                        .price(new BigDecimal("0"))
                        .maxDoctors(500)
                        .maxUsers(2000)
                        .maxAppointmentsPerMonth(200000)
                        .medicalRecordsModule(true)
                        .active(true)
                        .build()
        );

        planService.saveAll(plans);

        System.out.println("Planes sembrados correctamente");
    }

}
