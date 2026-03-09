package com.yuutoap.Appoiments.service.professionals.impl;

import com.yuutoap.Appoiments.config.tenant.TenantContext;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalRequestDTO;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;
import com.yuutoap.Appoiments.dto.professionals.mapper.DoctorMapper;
import com.yuutoap.Appoiments.model.consultingrooms.ConsultingRoom;
import com.yuutoap.Appoiments.model.professionals.Professional;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import com.yuutoap.Appoiments.repository.consultingrooms.ConsultingRoomRepository;
import com.yuutoap.Appoiments.repository.professionals.ProfessionalRepository;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.repository.specialties.SpecialtyRepository;
import com.yuutoap.Appoiments.service.professionals.contract.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final TenantRepository tenantRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorMapper doctorMappers;
    private final ConsultingRoomRepository consultingRoomRepository;

    @Override
    public List<ProfessionalResponseDTO> findProfessionals() {
        String tenantSlug = TenantContext.getTenant();
        return professionalRepository.findByTenantSlug(tenantSlug)
                .stream()
                .map(doctorMappers::mapToDTO)
                .toList();
    }

    @Override
    public ProfessionalResponseDTO findProfessionalById(UUID id) {
        String tenantSlug = TenantContext.getTenant();
        Professional professional = professionalRepository
                .findByIdAndTenantSlug(id, tenantSlug)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        return doctorMappers.mapToDTO(professional);
    }

    @Override
    public ProfessionalResponseDTO createProfessional(ProfessionalRequestDTO request) {

        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() -> new RuntimeException("Tenant no Encontrado"));

        ConsultingRoom consultingRoom = consultingRoomRepository
                .findById(request.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no Encontrado"));

        Set<Specialty> specialties = specialtyRepository
                .findAllById(request.getSpecialtyIds())
                .stream()
                .collect(Collectors.toSet());

        Professional professional = Professional.builder()
                .tenant(tenant)
                .identification(request.getIdentification())
                .consultingRoom(consultingRoom)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .licenseNumber(request.getLicenseNumber())
                .phone(request.getPhone())
                .bio(request.getBio())
                .specialties(specialties)
                .build();

        professionalRepository.save(professional);

        return doctorMappers.mapToDTO(professional);
    }

    @Override
    public ProfessionalResponseDTO updateProfessional(UUID id, ProfessionalRequestDTO request) {

        String tenantSlug = TenantContext.getTenant();

        Professional professional = professionalRepository.findByIdAndTenantSlug(id, tenantSlug)
                .orElseThrow(() -> new RuntimeException("Profesional no Encontrado"));

        Set<Specialty> specialties = specialtyRepository
                .findAllById(request.getSpecialtyIds())
                .stream()
                .collect(Collectors.toSet());

        professional.setIdentification(request.getIdentification());
        professional.setFirstName(request.getFirstName());
        professional.setLastName(request.getLastName());
        professional.setEmail(request.getEmail());
        professional.setLicenseNumber(request.getLicenseNumber());
        professional.setPhone(request.getPhone());
        professional.setBio(request.getBio());
        professional.setSpecialties(specialties);

        professionalRepository.save(professional);

        return doctorMappers.mapToDTO(professional);
    }

    @Override
    public void deleteProfessional(UUID id) {

        String tenantSlug = TenantContext.getTenant();
        professionalRepository.deleteByIdAndTenantSlug(id, tenantSlug);

    }



}
