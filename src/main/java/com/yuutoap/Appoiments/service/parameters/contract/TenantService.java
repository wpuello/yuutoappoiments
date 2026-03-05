package com.yuutoap.Appoiments.service.parameters.contract;

import com.yuutoap.Appoiments.model.parameters.Tenant;

import java.util.Optional;

public interface TenantService {
    Optional<Tenant> findBySlug(String slug);
    Tenant save(Tenant tenant);
}
