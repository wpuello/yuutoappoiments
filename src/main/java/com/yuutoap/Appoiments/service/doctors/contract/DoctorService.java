package com.yuutoap.Appoiments.service.doctors.contract;

import com.yuutoap.Appoiments.dto.doctors.DoctorRequestDTO;
import com.yuutoap.Appoiments.dto.doctors.DoctorResponseDTO;
import com.yuutoap.Appoiments.model.doctors.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    List<DoctorResponseDTO> findDoctors();
    DoctorResponseDTO findDoctorById(UUID id);
    DoctorResponseDTO createDoctor(DoctorRequestDTO request);
    DoctorResponseDTO updateDoctor(UUID id, DoctorRequestDTO request);
    void deleteDoctor(UUID id);

}
