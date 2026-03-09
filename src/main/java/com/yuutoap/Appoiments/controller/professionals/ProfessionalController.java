package com.yuutoap.Appoiments.controller.professionals;


import com.yuutoap.Appoiments.dto.professionals.ProfessionalRequestDTO;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;
import com.yuutoap.Appoiments.service.professionals.contract.ProfessionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/{tenant}/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findProfessionals(){
        return ResponseEntity.ok(professionalService.findProfessionals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> findProfessionalById(@PathVariable UUID id){
        return ResponseEntity.ok(professionalService.findProfessionalById(id));
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> createProfessional(
            @RequestBody ProfessionalRequestDTO request
    ){
        return ResponseEntity.ok(professionalService.createProfessional(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> updateProfessional(
            @PathVariable UUID id,
            @RequestBody ProfessionalRequestDTO request
    ){
        return ResponseEntity.ok(professionalService.updateProfessional(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable UUID id){
        professionalService.deleteProfessional(id);
        return ResponseEntity.noContent().build();
    }

}
