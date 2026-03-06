package com.yuutoap.Appoiments.dto.doctors.mapper;

import com.yuutoap.Appoiments.dto.doctors.DoctorResponseDTO;
import com.yuutoap.Appoiments.dto.specialties.SpecialtyDTO;
import com.yuutoap.Appoiments.model.doctors.Doctor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public DoctorResponseDTO mapToDTO(Doctor doctor){

        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .identification(doctor.getIdentification())
                .consultorioId(
                        doctor.getConsultorio() != null
                                ? doctor.getConsultorio().getId()
                                : null
                )
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .licenseNumber(doctor.getLicenseNumber())
                .phone(doctor.getPhone())
                .bio(doctor.getBio())
                .specialties(mapSpecialties(doctor))
                .build();
    }

    private Set<SpecialtyDTO> mapSpecialties(Doctor doctor){

        if(doctor.getSpecialties() == null){
            return Collections.emptySet();
        }

        return doctor.getSpecialties()
                .stream()
                .map(s -> SpecialtyDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build())
                .collect(Collectors.toSet());
    }

}
