package com.yuutoap.Appoiments.dto.professionals.mapper;

import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;
import com.yuutoap.Appoiments.dto.specialties.SpecialtyDTO;
import com.yuutoap.Appoiments.model.professionals.Professional;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProfessionalMapper {

    public ProfessionalResponseDTO mapToDTO(Professional professional){

        return ProfessionalResponseDTO.builder()
                .id(professional.getId())
                .identification(professional.getIdentification())
                .consultorioId(
                        professional.getConsultingRoom() != null
                                ? professional.getConsultingRoom().getId()
                                : null
                )
                .firstName(professional.getFirstName())
                .lastName(professional.getLastName())
                .email(professional.getEmail())
                .licenseNumber(professional.getLicenseNumber())
                .phone(professional.getPhone())
                .bio(professional.getBio())
                .specialties(mapSpecialties(professional))
                .build();
    }

    private Set<SpecialtyDTO> mapSpecialties(Professional professional){

        if(professional.getSpecialties() == null){
            return Collections.emptySet();
        }

        return professional.getSpecialties()
                .stream()
                .map(s -> SpecialtyDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build())
                .collect(Collectors.toSet());
    }

}
