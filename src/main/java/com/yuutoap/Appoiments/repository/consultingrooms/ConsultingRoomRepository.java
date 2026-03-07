package com.yuutoap.Appoiments.repository.consultingrooms;

import com.yuutoap.Appoiments.model.consultingrooms.ConsultingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ConsultingRoomRepository extends JpaRepository<ConsultingRoom, UUID> {

    List<ConsultingRoom> findByTenantSlug(String slug);
    Optional<ConsultingRoom> findByIdAndTenantSlug(UUID id, String slug);
    List<ConsultingRoom> findByTenantSlugAndActiveTrue(String slug);

}
