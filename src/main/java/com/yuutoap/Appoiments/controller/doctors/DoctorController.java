package com.yuutoap.Appoiments.controller.doctors;


import com.yuutoap.Appoiments.dto.doctors.DoctorRequestDTO;
import com.yuutoap.Appoiments.dto.doctors.DoctorResponseDTO;
import com.yuutoap.Appoiments.service.auth.AuthService;
import com.yuutoap.Appoiments.service.doctors.contract.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<List<DoctorResponseDTO>> findDoctors(){
        return ResponseEntity.ok(doctorService.findDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findDoctorById(@PathVariable UUID id){
        return ResponseEntity.ok(doctorService.findDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(
            @RequestBody DoctorRequestDTO request
    ){
        return ResponseEntity.ok(doctorService.createDoctor(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable UUID id,
            @RequestBody DoctorRequestDTO request
    ){
        return ResponseEntity.ok(doctorService.updateDoctor(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable UUID id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
