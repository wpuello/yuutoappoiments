package com.yuutoap.Appoiments.repository.parameters;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.parameters.TenantParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantParametersRepository extends JpaRepository<TenantParameters, UUID> {
    Optional<TenantParameters> findByTenant(Tenant tenant);
}
