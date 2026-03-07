package com.yuutoap.Appoiments.dto.professionals;

import com.yuutoap.Appoiments.dto.specialties.SpecialtyDTO;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalResponseDTO {

    private UUID id;
    private UUID consultorioId;
    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private String licenseNumber;
    private String phone;
    private String bio;
    private Set<SpecialtyDTO> specialties;
}
