package com.yuutoap.Appoiments.dto.specialties;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    private UUID id;
    private String name;
}
