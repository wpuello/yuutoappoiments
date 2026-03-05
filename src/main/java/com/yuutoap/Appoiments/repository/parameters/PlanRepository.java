package com.yuutoap.Appoiments.repository.parameters;

import com.yuutoap.Appoiments.model.parameters.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
    Optional<Plan> findByName(String name);
}
