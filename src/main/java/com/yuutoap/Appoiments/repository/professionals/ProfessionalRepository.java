package com.yuutoap.Appoiments.repository.professionals;

import com.yuutoap.Appoiments.model.professionals.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
    List<Professional> findByTenantSlug(String tenant);
    Optional<Professional> findByIdAndTenantSlug(UUID id, String tenantSlug);
    void deleteByIdAndTenantSlug(UUID id, String tenantSlug);
}
