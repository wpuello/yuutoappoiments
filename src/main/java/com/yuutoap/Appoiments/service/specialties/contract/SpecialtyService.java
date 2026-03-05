package com.yuutoap.Appoiments.service.specialties.contract;

import com.yuutoap.Appoiments.model.specialties.Specialty;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpecialtyService {

    Optional<Specialty> findById(UUID id);
    Optional<Specialty> findByName(String name);
    List<Specialty> findAll();
    Specialty save(Specialty specialty);
    List<Specialty> saveAll(List<Specialty> specialties);
}
