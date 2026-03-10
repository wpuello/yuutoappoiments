package com.yuutoap.Appoiments.controller.professionals;


import com.yuutoap.Appoiments.handlers.ApiGeneralResponses;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalRequestDTO;
import com.yuutoap.Appoiments.dto.professionals.ProfessionalResponseDTO;
import com.yuutoap.Appoiments.service.professionals.contract.ProfessionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/{tenant}/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @GetMapping
    public ResponseEntity<ApiGeneralResponses<List<ProfessionalResponseDTO>>> findProfessionals(){

        List<ProfessionalResponseDTO> professionals = professionalService.findProfessionals();

        ApiGeneralResponses<List<ProfessionalResponseDTO>> response =
                ApiGeneralResponses.<List<ProfessionalResponseDTO>>builder()
                        .success(true)
                        .message("Lista de profesionales")
                        .data(professionals)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGeneralResponses<ProfessionalResponseDTO>> findProfessionalById(@PathVariable UUID id){

        ProfessionalResponseDTO professional = professionalService.findProfessionalById(id);

        ApiGeneralResponses<ProfessionalResponseDTO> response =
                ApiGeneralResponses.<ProfessionalResponseDTO>builder()
                        .success(true)
                        .message("Profesional encontrado")
                        .data(professional)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiGeneralResponses<ProfessionalResponseDTO>> createProfessional(
            @Valid @RequestBody ProfessionalRequestDTO request
    ){

        ProfessionalResponseDTO professional = professionalService.createProfessional(request);

        ApiGeneralResponses<ProfessionalResponseDTO> response =
                ApiGeneralResponses.<ProfessionalResponseDTO>builder()
                        .success(true)
                        .message("Professional creado exitosamente")
                        .data(professional)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGeneralResponses<ProfessionalResponseDTO>> updateProfessional(
            @PathVariable UUID id,
            @Valid @RequestBody ProfessionalRequestDTO request
    ){

        ProfessionalResponseDTO professional = professionalService.updateProfessional(id, request);

        ApiGeneralResponses<ProfessionalResponseDTO> response =
                ApiGeneralResponses.<ProfessionalResponseDTO>builder()
                        .success(true)
                        .message("Professional actualizado exitosamente")
                        .data(professional)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiGeneralResponses<Map<String, UUID>>> deleteProfessional(
            @PathVariable UUID id){
        professionalService.deleteProfessional(id);

        Map<String, UUID> data = Map.of("id", id);

        ApiGeneralResponses<Map<String, UUID>> response = ApiGeneralResponses.<Map<String, UUID>>builder()
                .success(true)
                .message("Professional Eliminado Exitosamente")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);

    }

}
