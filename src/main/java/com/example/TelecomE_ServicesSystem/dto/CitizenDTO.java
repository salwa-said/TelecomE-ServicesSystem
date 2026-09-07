package com.example.TelecomE_ServicesSystem.dto;

import com.example.TelecomE_ServicesSystem.entites.Citizen;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitizenDTO {
    @Positive(message = "ID must be positive")
    private Long citizenId;
    @NotBlank(message = "Citizen name cannot be blank")
    @Size(min = 3, max = 100, message = "Citizen name must be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "National ID cannot be blank")
    @Size(min = 3, max = 50, message = "National ID must be between 3 and 50 characters")
    private String nationalId;
    @Size(max = 30, message = "Phone number cannot exceed 30 characters")
    private String phoneNumber;
    @NotBlank(message = "Citizen email cannot be blank")
    @Email(message = "Citizen email is invalid")
    private String email;

    public static CitizenDTO convertToDTO(Citizen entity) {
        if (entity == null) return null;
        return CitizenDTO.builder()
                .citizenId(entity.getId())
                .name(entity.getName())
                .nationalId(entity.getNationalId())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();
    }

    public static List<CitizenDTO> convertToDTO(List<Citizen> entityList) {
        List<CitizenDTO> dtos = new ArrayList<>();
        for (Citizen entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}