package com.yuutoap.Appoiments.seeder;

import com.yuutoap.Appoiments.service.branches.contract.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6) // ORDEN DE EJECUCION DE SEEDERS
@RequiredArgsConstructor
public class BranchSeeder implements CommandLineRunner {
    private final BranchService branchSeederService;

    @Override
    public void run(String... args) {
        branchSeederService.seedBranches();
    }
}
