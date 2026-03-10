package com.yuutoap.Appoiments.service.consultingrooms.impl;

import com.yuutoap.Appoiments.model.branches.Branch;
import com.yuutoap.Appoiments.model.consultingrooms.ConsultingRoom;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.repository.branches.BranchRepository;
import com.yuutoap.Appoiments.repository.consultingrooms.ConsultingRoomRepository;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.service.consultingrooms.contract.ConsultingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ConsultingRoomServiceImpl implements ConsultingRoomService {

    private final ConsultingRoomRepository consultingRoomRepository;
    private final TenantRepository tenantRepository;
    private final BranchRepository branchRepository;

    @Override
    public void seedConsultingRooms() {

        if (consultingRoomRepository.count() > 0) {
            return;
        }

        Tenant tenant = tenantRepository.findAll().stream().findFirst().orElse(null);
        Branch branch = branchRepository.findAll().stream().findFirst().orElse(null);

        if (tenant == null || branch == null) {
            return;
        }

        List<ConsultingRoom> rooms = List.of(

                ConsultingRoom.builder()
                        .tenant(tenant)
                        .branch(branch)
                        .name("Consultorio 101")
                        .code("CR101")
                        .floor("1")
                        .building("Edificio A")
                        .description("Consultorio de medicina general")
                        .active(true)
                        .build(),

                ConsultingRoom.builder()
                        .tenant(tenant)
                        .branch(branch)
                        .name("Consultorio 102")
                        .code("CR102")
                        .floor("1")
                        .building("Edificio A")
                        .description("Consultorio de cardiología")
                        .active(true)
                        .build(),

                ConsultingRoom.builder()
                        .tenant(tenant)
                        .branch(branch)
                        .name("Consultorio 201")
                        .code("CR201")
                        .floor("2")
                        .building("Edificio B")
                        .description("Consultorio de pediatría")
                        .active(true)
                        .build()

        );

        consultingRoomRepository.saveAll(rooms);
    }


}
