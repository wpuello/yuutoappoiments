package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.service.consultingrooms.contract.ConsultingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class ConsultingRoomSeeder implements CommandLineRunner {

    private final ConsultingRoomService consultingRoomSeederService;

    @Override
    public void run(String... args) {
        consultingRoomSeederService.seedConsultingRooms();
    }
}
