package com.yuutoap.Appoiments.repository.professionals;

import com.yuutoap.Appoiments.model.professionals.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
    List<Professional> findByTenantSlug(String tenant);
    Optional<Professional> findByIdAndTenantId(UUID id, UUID tenantId);
    void deleteByIdAndTenantId(UUID id, UUID tenantId);
    boolean existsByIdentificationAndTenantId(String identification, UUID tenantId);
    boolean existsByIdentificationAndTenantIdAndIdNot(String identification, UUID tenantId, UUID id
    );
}
