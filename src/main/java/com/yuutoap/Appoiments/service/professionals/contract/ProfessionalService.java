package com.yuutoap.Appoiments.service.professionals.contract;

import com.yuutoap.Appoiments.dto.professionals.ProfessionalRequestDTO;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProfessionalService {

    List<ProfessionalResponseDTO> findProfessionals();
    ProfessionalResponseDTO findProfessionalById(UUID id);
    ProfessionalResponseDTO createProfessional(ProfessionalRequestDTO request);
    ProfessionalResponseDTO updateProfessional(UUID id, ProfessionalRequestDTO request);
    void deleteProfessional(UUID id);

}
