package com.yuutoap.Appoiments.dto.professionals;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalRequestDTO {

    @NotNull(message = "Consultorio is required")
    private UUID consultorioId;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^[0-9]+$", message = "La identificación debe ser numérica")
    private String identification;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Formato de Email Invalido")
    private String email;

    private String licenseNumber;

    private String phone;

    private String bio;

    @NotEmpty(message = "AL menos una especialidad es Requerida")
    private List<UUID> specialtyIds;

}
