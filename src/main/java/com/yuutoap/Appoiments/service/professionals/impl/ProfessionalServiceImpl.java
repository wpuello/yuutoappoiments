package com.yuutoap.Appoiments.service.professionals.impl;

import com.yuutoap.Appoiments.config.tenant.TenantContext;
import com.yuutoap.Appoiments.handlers.GlobalConflictException;
import com.yuutoap.Appoiments.handlers.GlobalResourceNotFoundException;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalRequestDTO;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;
import com.yuutoap.Appoiments.dto.professionals.mapper.ProfessionalMapper;
import com.yuutoap.Appoiments.model.consultingrooms.ConsultingRoom;
import com.yuutoap.Appoiments.model.professionals.Professional;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import com.yuutoap.Appoiments.repository.consultingrooms.ConsultingRoomRepository;
import com.yuutoap.Appoiments.repository.professionals.ProfessionalRepository;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.repository.specialties.SpecialtyRepository;
import com.yuutoap.Appoiments.service.professionals.contract.ProfessionalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final TenantRepository tenantRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ProfessionalMapper professionalMappers;
    private final ConsultingRoomRepository consultingRoomRepository;

    @Override
    public List<ProfessionalResponseDTO> findProfessionals() {
        String tenantSlug = TenantContext.getTenant();
        return professionalRepository.findByTenantSlug(tenantSlug)
                .stream()
                .map(professionalMappers::mapToDTO)
                .toList();
    }

    @Override
    public ProfessionalResponseDTO findProfessionalById(UUID id) {
        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Tenant no encontrado"));

        Professional professional = professionalRepository.findByIdAndTenantId(id, tenant.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));

        return professionalMappers.mapToDTO(professional);
    }

    @Override
    public ProfessionalResponseDTO createProfessional(ProfessionalRequestDTO request) {

        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() ->
                        new GlobalResourceNotFoundException("Tenant no encontrado"));

        // Validar identificación única por tenant
        boolean exists = professionalRepository
                .existsByIdentificationAndTenantId(request.getIdentification(), tenant.getId());

        if (exists) {
            throw new GlobalConflictException(
                    "Ya existe un profesional con esta misma identificación en este tenant"
            );
        }

        // Validar consultorio
        ConsultingRoom consultingRoom = consultingRoomRepository
                .findByIdAndTenantId(request.getConsultorioId(), tenant.getId())
                .orElseThrow(() ->
                        new GlobalResourceNotFoundException("Consultorio no encontrado"));

        // Validar especialidades
        List<UUID> specialtyIds = request.getSpecialtyIds();
        if (specialtyIds == null || specialtyIds.isEmpty()) {
            throw new GlobalConflictException("Debe enviar al menos una especialidad");
        }

        // Validar duplicados
        Set<UUID> uniqueIds = new HashSet<>(specialtyIds);
        if (uniqueIds.size() != specialtyIds.size()) {
            throw new GlobalConflictException("No se permiten especialidades duplicadas");
        }

        // Validar especialidades
        List<Specialty> specialtiesList = specialtyRepository
                .findAllByIdInAndTenantId(specialtyIds, tenant.getId());

        Set<UUID> foundIds = specialtiesList.stream()
                .map(Specialty::getId)
                .collect(Collectors.toSet());

        Set<UUID> missingIds = new HashSet<>(specialtyIds);
        missingIds.removeAll(foundIds);

        if (!missingIds.isEmpty()) {
            throw new GlobalResourceNotFoundException(
                    "No existen las especialidades con ids: " + missingIds
            );
        }

        Set<Specialty> specialties = new HashSet<>(specialtiesList);

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

        return professionalMappers.mapToDTO(professional);
    }

    @Override
    public ProfessionalResponseDTO updateProfessional(UUID id, ProfessionalRequestDTO request) {

        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() ->
                        new GlobalResourceNotFoundException("Tenant no encontrado"));

        // Buscar el profesional a actualizar
        Professional professional = professionalRepository
                .findByIdAndTenantId(id, tenant.getId())
                .orElseThrow(() ->
                        new GlobalResourceNotFoundException("Profesional no encontrado"));


        // Validar identificación única (solo si cambia)
        String identification = request.getIdentification().trim();
        boolean exists = professionalRepository
                .existsByIdentificationAndTenantIdAndIdNot(
                        request.getIdentification(),
                        tenant.getId(),
                        id
                );

        System.out.println("IDENTIFICATION: " + request.getIdentification());
        System.out.println("TENANT: " + tenant.getId());
        System.out.println("ID: " + id);
        System.out.println("EXISTS: " + exists);

        if (exists) {
            throw new GlobalConflictException(
                    "Ya existe otro profesional con esta misma identificación en este tenant"
            );
        }

        // Validar consultorio
        ConsultingRoom consultingRoom = consultingRoomRepository
                .findByIdAndTenantId(request.getConsultorioId(), tenant.getId())
                .orElseThrow(() ->
                        new GlobalResourceNotFoundException("Consultorio no encontrado"));

        // Validar especialidades
        List<UUID> specialtyIds = request.getSpecialtyIds();
        if (specialtyIds == null || specialtyIds.isEmpty()) {
            throw new GlobalConflictException("Debe enviar al menos una especialidad");
        }

        // Validar duplicados
        Set<UUID> uniqueIds = new HashSet<>(specialtyIds);
        if (uniqueIds.size() != specialtyIds.size()) {
            throw new GlobalConflictException("No se permiten especialidades duplicadas");
        }


        // Validar especialidades
        List<Specialty> specialtiesList = specialtyRepository
                .findAllByIdInAndTenantId(specialtyIds, tenant.getId());


        Set<UUID> foundIds = specialtiesList.stream()
                .map(Specialty::getId)
                .collect(Collectors.toSet());

        Set<UUID> missingIds = new HashSet<>(specialtyIds);
        missingIds.removeAll(foundIds);

        if (!missingIds.isEmpty()) {
            throw new GlobalResourceNotFoundException(
                    "No existen las especialidades con ids: " + missingIds
            );
        }

        Set<Specialty> specialties = new HashSet<>(specialtiesList);

        // Actualizar campos
        professional.setIdentification(request.getIdentification());
        professional.setConsultingRoom(consultingRoom);
        professional.setFirstName(request.getFirstName());
        professional.setLastName(request.getLastName());
        professional.setEmail(request.getEmail());
        professional.setLicenseNumber(request.getLicenseNumber());
        professional.setPhone(request.getPhone());
        professional.setBio(request.getBio());
        professional.setSpecialties(specialties);

        professionalRepository.save(professional);

        return professionalMappers.mapToDTO(professional);
    }

    @Override
    public void deleteProfessional(UUID id) {

        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Tenant no encontrado"));

        Professional professional = professionalRepository.findByIdAndTenantId(id, tenant.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado"));

        professionalRepository.deleteByIdAndTenantId(id, tenant.getId());

    }



}
