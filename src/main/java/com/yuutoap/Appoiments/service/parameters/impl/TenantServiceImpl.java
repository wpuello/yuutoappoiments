package com.yuutoap.Appoiments.service.parameters.impl;



import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.service.parameters.contract.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Override
    public Optional<Tenant> findBySlug(String slug) {
        return tenantRepository.findBySlug(slug);
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }


}
