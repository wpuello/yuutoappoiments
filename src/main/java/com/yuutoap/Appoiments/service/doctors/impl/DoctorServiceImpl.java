package com.yuutoap.Appoiments.service.doctors.impl;

import com.yuutoap.Appoiments.config.tenant.TenantContext;
import com.yuutoap.Appoiments.dto.doctors.DoctorRequestDTO;
import com.yuutoap.Appoiments.dto.doctors.DoctorResponseDTO;
import com.yuutoap.Appoiments.dto.doctors.mapper.DoctorMapper;
import com.yuutoap.Appoiments.model.consultorios.Consultorio;
import com.yuutoap.Appoiments.model.doctors.Doctor;
import com.yuutoap.Appoiments.model.parameters.Tenant;
import com.yuutoap.Appoiments.model.specialties.Specialty;
import com.yuutoap.Appoiments.repository.consultorios.ConsultorioRepository;
import com.yuutoap.Appoiments.repository.doctors.DoctorRepository;
import com.yuutoap.Appoiments.repository.parameters.TenantRepository;
import com.yuutoap.Appoiments.repository.specialties.SpecialtyRepository;
import com.yuutoap.Appoiments.service.doctors.contract.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final TenantRepository tenantRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorMapper doctorMappers;
    private final ConsultorioRepository consultorioRepository;

    @Override
    public List<DoctorResponseDTO> findDoctors() {
        String tenantSlug = TenantContext.getTenant();
        return doctorRepository.findByTenantSlug(tenantSlug)
                .stream()
                .map(doctorMappers::mapToDTO)
                .toList();
    }

    @Override
    public DoctorResponseDTO findDoctorById(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no Encontrado"));
        return doctorMappers.mapToDTO(doctor);
    }

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO request) {

        String tenantSlug = TenantContext.getTenant();

        Tenant tenant = tenantRepository.findBySlug(tenantSlug)
                .orElseThrow(() -> new RuntimeException("Tenant no Encontrado"));

        Consultorio consultorio = consultorioRepository
                .findById(request.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no Encontrado"));

        Set<Specialty> specialties = specialtyRepository
                .findAllById(request.getSpecialtyIds())
                .stream()
                .collect(Collectors.toSet());

        Doctor doctor = Doctor.builder()
                .tenant(tenant)
                .identification(request.getIdentification())
                .consultorio(consultorio)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .licenseNumber(request.getLicenseNumber())
                .phone(request.getPhone())
                .bio(request.getBio())
                .specialties(specialties)
                .build();

        doctorRepository.save(doctor);

        return doctorMappers.mapToDTO(doctor);
    }

    @Override
    public DoctorResponseDTO updateDoctor(UUID id, DoctorRequestDTO request) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no Encontrado"));

        Set<Specialty> specialties = specialtyRepository
                .findAllById(request.getSpecialtyIds())
                .stream()
                .collect(Collectors.toSet());

        doctor.setIdentification(request.getIdentification());
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setPhone(request.getPhone());
        doctor.setBio(request.getBio());
        doctor.setSpecialties(specialties);

        doctorRepository.save(doctor);

        return doctorMappers.mapToDTO(doctor);
    }

    @Override
    public void deleteDoctor(UUID id) {
        doctorRepository.deleteById(id);
    }



}
