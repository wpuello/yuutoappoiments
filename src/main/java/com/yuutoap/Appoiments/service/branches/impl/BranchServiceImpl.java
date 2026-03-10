package com.yuutoap.Appoiments.service.branches.impl;

import com.yuutoap.Appoiments.model.branches.Branch;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.repository.branches.BranchRepository;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.service.branches.contract.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final TenantRepository tenantRepository;

    @Override
    public void seedBranches() {

        if (branchRepository.count() > 0) {
            return;
        }

        Tenant tenant = tenantRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);

        if (tenant == null) {
            return;
        }

        List<Branch> branches = List.of(

                Branch.builder()
                        .tenant(tenant)
                        .name("Sede Centro")
                        .code("CENTRO")
                        .address("Av. Principal #123")
                        .city("Cartagena")
                        .phone("+57 3001112233")
                        .email("centro@yuuto.com")
                        .active(true)
                        .build(),

                Branch.builder()
                        .tenant(tenant)
                        .name("Sede Bocagrande")
                        .code("BOC")
                        .address("Calle 8 #45-12")
                        .city("Cartagena")
                        .phone("+57 3002223344")
                        .email("bocagrande@yuuto.com")
                        .active(true)
                        .build(),

                Branch.builder()
                        .tenant(tenant)
                        .name("Sede Norte")
                        .code("NORTE")
                        .address("Cra 15 #80-20")
                        .city("Cartagena")
                        .phone("+57 3003334455")
                        .email("norte@yuuto.com")
                        .active(true)
                        .build()

        );

        branchRepository.saveAll(branches);
    }
}
