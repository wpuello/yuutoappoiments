package com.yuutoap.Appoiments.service.parameters.impl;

import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.parameters.TenantParameters;
import com.yuutoap.Appoiments.repository.parameters.TenantParametersRepository;
import com.yuutoap.Appoiments.service.parameters.contract.TenantParametersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantParametersServiceImpl implements TenantParametersService {

    private final TenantParametersRepository tenantParametersRepository;

    @Override
    public TenantParameters save(TenantParameters parameters) {
        return tenantParametersRepository.save(parameters);
    }

    @Override
    public Optional<TenantParameters> findByTenant(Tenant tenant) {
        return tenantParametersRepository.findByTenant(tenant);
    }

    @Override
    public Optional<TenantParameters> findById(UUID id) {
        return tenantParametersRepository.findById(id);
    }

}
