package com.yuutoap.Appoiments.service.specialties.impl;

import com.yuutoap.Appoiments.model.specialties.Specialty;
import com.yuutoap.Appoiments.repository.specialties.SpecialtyRepository;
import com.yuutoap.Appoiments.service.specialties.contract.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Optional<Specialty> findById(UUID id) {
        return specialtyRepository.findById(id);
    }

    @Override
    public Optional<Specialty> findByName(String name) {
        return specialtyRepository.findByName(name);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public List<Specialty> saveAll(List<Specialty> specialties) {
        return specialtyRepository.saveAll(specialties);
    }
}
