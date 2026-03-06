package com.yuutoap.Appoiments.repository.consultorios;

import com.yuutoap.Appoiments.model.consultorios.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ConsultorioRepository extends JpaRepository<Consultorio, UUID> {

    List<Consultorio> findByTenantSlug(String slug);
    Optional<Consultorio> findByIdAndTenantSlug(UUID id, String slug);
    List<Consultorio> findByTenantSlugAndActiveTrue(String slug);

}
