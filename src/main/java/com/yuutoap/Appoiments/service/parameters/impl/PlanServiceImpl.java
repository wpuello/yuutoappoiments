package com.yuutoap.Appoiments.service.parameters.impl;

import com.yuutoap.Appoiments.model.parameters.Plan;
import com.yuutoap.Appoiments.repository.parameters.PlanRepository;
import com.yuutoap.Appoiments.service.parameters.contract.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public Optional<Plan> findById(UUID id) {
        return planRepository.findById(id);
    }

    @Override
    public Optional<Plan> findByName(String name) {
        return planRepository.findByName(name);
    }

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> saveAll(List<Plan> plans) {
        return planRepository.saveAll(plans);
    }

    @Override
    public boolean existsByName(String name) {
        return planRepository.findByName(name).isPresent();
    }
}
