package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import com.yuutoap.Appoiments.service.parameters.contract.TenantService;
import com.yuutoap.Appoiments.service.specialties.contract.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Order(5) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class SpecialtySeeder implements CommandLineRunner {

    private final SpecialtyService specialtyService;
    private final TenantService tenantService;

    @Override
    public void run(String... args) {

        Tenant tenant = tenantService.findBySlug("yuuto-system")
                .orElseThrow(() -> new RuntimeException("Tenant yuuto-system not found"));

        if (!specialtyService.findAll().isEmpty()) {
            return;
        }

        List<Specialty> specialties = List.of(

                Specialty.builder()
                        .tenant(tenant)
                        .name("Medicina General")
                        .description("Consulta externa inicial, urgencias básicas y triaje")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Medicina Interna")
                        .description("Diagnóstico y tratamiento de enfermedades complejas en adultos")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Cardiología")
                        .description("Diagnóstico y tratamiento de enfermedades Cardiovasculares")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Pediatría")
                        .description("Cuidado integral de la salud infantil")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Medicina Familiar")
                        .description("Atención primaria integral para todas las edades")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Medicina Crítica y Cuidado Intensivo")
                        .description("Manejo de pacientes en estado crítico")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Geriatría")
                        .description("Atención médica especializada en adultos mayores")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Psiquiatría")
                        .description("Diagnóstico y tratamiento de trastornos mentales")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Neurología")
                        .description("Tratamiento de enfermedades del sistema nervioso")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Infectología")
                        .description("Diagnóstico y tratamiento de enfermedades infecciosas")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Ginecología y Obstetricia")
                        .description("Salud reproductiva de la mujer y control del embarazo")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Cirugía General")
                        .description("Procedimientos quirúrgicos generales")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Ortopedia y Traumatología")
                        .description("Lesiones óseas y musculoesqueléticas")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Anestesiología y Reanimación")
                        .description("Manejo anestésico en procedimientos quirúrgicos")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Oftalmología")
                        .description("Diagnóstico y tratamiento de enfermedades oculares")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Otorrinolaringología")
                        .description("Tratamiento de oído, nariz y garganta")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Cirugía Plástica, Estética y Reconstructiva")
                        .description("Procedimientos reconstructivos y estéticos")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Radiología e Imágenes Diagnósticas")
                        .description("Diagnóstico mediante imágenes médicas")
                        .active(true)
                        .build(),

                Specialty.builder()
                        .tenant(tenant)
                        .name("Patología")
                        .description("Estudio anatómico y clínico de enfermedades")
                        .active(true)
                        .build()

        );

        specialtyService.saveAll(specialties);

        System.out.println("Especialidades medicas Basicas creadas correctamente");
    }
}
