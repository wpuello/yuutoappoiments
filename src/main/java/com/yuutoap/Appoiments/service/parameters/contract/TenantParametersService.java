package com.yuutoap.Appoiments.service.parameters.contract;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.parameters.TenantParameters;
import java.util.Optional;
import java.util.UUID;

public interface TenantParametersService {

    TenantParameters save(TenantParameters parameters);
    Optional<TenantParameters> findByTenant(Tenant tenant);
    Optional<TenantParameters> findById(UUID id);
}
