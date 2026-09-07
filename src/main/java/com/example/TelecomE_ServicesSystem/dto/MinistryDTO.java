package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Ministry;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinistryDTO {
    @Positive(message = "ID must be positive")
    private Long ministryId;
    @NotBlank(message = "Ministry name cannot be blank")
    @Size(min = 3, max = 100, message = "Ministry name must be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "Ministry address cannot be blank")
    @Size(min = 5, max = 150, message = "Ministry address must be between 5 and 150 characters")
    private String address;

    public static MinistryDTO convertToDTO(Ministry entity) {
        if (entity == null) return null;
        return MinistryDTO.builder()
                .ministryId(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }

    public static List<MinistryDTO> convertToDTO(List<Ministry> entityList) {
        List<MinistryDTO> dtos = new ArrayList<>();
        for (Ministry entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

