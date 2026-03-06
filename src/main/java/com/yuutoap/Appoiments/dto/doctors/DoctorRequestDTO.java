package com.yuutoap.Appoiments.dto.doctors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {

    @NotBlank(message = "Consultorio is required")
    private UUID consultorioId;

    @NotBlank(message = "Identification is required")
    private String identification;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String licenseNumber;

    private String phone;

    private String bio;

    @NotEmpty(message = "At least one specialty is required")
    private Set<UUID> specialtyIds;

}
