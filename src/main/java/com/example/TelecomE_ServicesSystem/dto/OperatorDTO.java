package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Operator;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperatorDTO {
    @Positive(message = "ID must be positive")
    private Long operatorId;
    @NotBlank(message = "Operator name cannot be blank")
    @Size(min = 2, max = 100, message = "Operator name must be between 2 and 100 characters")
    private String name;
    @NotBlank(message = "License number cannot be blank")
    @Size(min = 2, max = 80, message = "License number must be between 2 and 80 characters")
    private String licenseNumber;
    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Contact email is invalid")
    private String contactEmail;
    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 80, message = "Country must be between 2 and 80 characters")
    private String country;

    public static OperatorDTO convertToDTO(Operator entity) {
        if (entity == null) return null;
        return OperatorDTO.builder()
                .operatorId(entity.getId())
                .name(entity.getName())
                .licenseNumber(entity.getLicenseNumber())
                .contactEmail(entity.getContactEmail())
                .country(entity.getCountry())
                .build();
    }

    public static List<OperatorDTO> convertToDTO(List<Operator> entityList) {
        List<OperatorDTO> dtos = new ArrayList<>();
        for (Operator entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

