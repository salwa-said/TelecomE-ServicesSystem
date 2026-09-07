package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.DomainRegistration;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainRegistrationDTO {
    @Positive(message = "ID must be positive")
    private Long domainRegistrationId;
    @NotBlank(message = "Domain name cannot be blank")
    @Size(min = 3, max = 150, message = "Domain name must be between 3 and 150 characters")
    private String domainName;
    @NotNull(message = "Registered date is required")
    @PastOrPresent(message = "Registered date cannot be in the future")
    private LocalDate registeredDate;
    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;
    @NotBlank(message = "Domain status cannot be blank")
    @Size(max = 30, message = "Status cannot exceed 30 characters")
    private String status;
    @NotNull(message = "Related ID is required")
    private Long citizenId;

    public static DomainRegistrationDTO convertToDTO(DomainRegistration entity) {
        if (entity == null) return null;
        return DomainRegistrationDTO.builder()
                .domainRegistrationId(entity.getId())
                .domainName(entity.getDomainName())
                .registeredDate(entity.getRegisteredDate())
                .expiryDate(entity.getExpiryDate())
                .status(entity.getStatus())
                .citizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : null)
                .build();
    }

    public static List<DomainRegistrationDTO> convertToDTO(List<DomainRegistration> entityList) {
        List<DomainRegistrationDTO> dtos = new ArrayList<>();
        for (DomainRegistration entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

