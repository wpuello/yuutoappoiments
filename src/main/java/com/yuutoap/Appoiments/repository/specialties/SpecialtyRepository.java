package com.yuutoap.Appoiments.repository.specialties;

import com.yuutoap.Appoiments.model.specialties.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SpecialtyRepository extends JpaRepository<Specialty, UUID>{
    Optional<Specialty> findByName(String name);
    List<Specialty> findAllByIdInAndTenantId(List<UUID> ids, UUID tenantId);
}
