package com.yuutoap.Appoiments.service.parameters.contract;

import com.yuutoap.Appoiments.model.parameters.Plan;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanService {

    Optional<Plan> findById(UUID id);
    Optional<Plan> findByName(String name);
    List<Plan> findAll();
    Plan save(Plan plan);
    List<Plan> saveAll(List<Plan> plans);
    boolean existsByName(String name);
}
