package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.model.parameters.Plan;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.parameters.TenantParameters;
import com.yuutoap.Appoiments.service.parameters.contract.PlanService;
import com.yuutoap.Appoiments.service.parameters.contract.TenantParametersService;
import com.yuutoap.Appoiments.service.parameters.contract.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class TenantSeeder implements CommandLineRunner{

    private final TenantService tenantService;
    private final PlanService planService;
    private final TenantParametersService tenantParametersService;

    @Override
    public void run(String... args) {

        if (tenantService.findBySlug("yuuto-system").isPresent()) {
            return;
        }

        Plan systemPlan = planService.findByName("ADMINISTRADOR")
                .orElseThrow(() -> new RuntimeException("Plan ADMINISTRADOR not found"));

        Tenant tenant = Tenant.builder()
                .nit("900000000")
                .businessName("YUUTO SYSTEM")
                .legalRepresentativeName("Super Administrator")
                .legalRepresentativeId("9299215")
                .slug("yuuto-system")
                .finalUrlClient(null)
                .email("sistemas@yuuto.com.co")
                .phone("0000000000")
                .address("Parque Heredia Cartagena")
                .city("Cartagena")
                .country("Colombia")
                .logoUrl(null)
                .plan(systemPlan)
                .status("ACTIVE")
                .observations("System tenant for super administrator")
                .build();

        tenantService.save(tenant);

       TenantParameters parameters = TenantParameters.builder()
                .tenant(tenant)
                .sendEmailNotifications(true)
                .emailNotifications("sistemas@yuuto.com.co")
                .sendWhatsappNotifications(false)
                .whatsappNumberNotifications("+573001234567")
                .reminderHoursBefore(3)
                .cancelationHoursBefore(6)
                .allowOnlineBooking(true)
                .patientRescheduleCount(2)
                .externalIntegrations(false)
                .build();

        tenantParametersService.save(parameters);

        System.out.println("Tenant principal creado correctamente");
    }
}
