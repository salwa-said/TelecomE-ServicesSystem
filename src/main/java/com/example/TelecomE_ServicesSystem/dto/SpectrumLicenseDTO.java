package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.SpectrumLicense;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpectrumLicenseDTO {
    @Positive(message = "ID must be positive")
    private Long spectrumLicenseId;
    @NotBlank(message = "Band name cannot be blank")
    @Size(max = 80, message = "Band name cannot exceed 80 characters")
    private String bandName;
    @NotNull(message = "Frequency is required")
    @Positive(message = "Frequency must be positive")
    private BigDecimal frequencyMhz;
    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date cannot be in the future")
    private LocalDate issueDate;
    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;
    @NotBlank(message = "License status cannot be blank")
    @Size(max = 30, message = "Status cannot exceed 30 characters")
    private String status;
    @NotNull(message = "Related ID is required")
    private Long operatorId;

    public static SpectrumLicenseDTO convertToDTO(SpectrumLicense entity) {
        if (entity == null) return null;
        return SpectrumLicenseDTO.builder()
                .spectrumLicenseId(entity.getId())
                .bandName(entity.getBandName())
                .frequencyMhz(entity.getFrequencyMhz())
                .issueDate(entity.getIssueDate())
                .expiryDate(entity.getExpiryDate())
                .status(entity.getStatus())
                .operatorId(entity.getOperator() != null ? entity.getOperator().getId() : null)
                .build();
    }

    public static List<SpectrumLicenseDTO> convertToDTO(List<SpectrumLicense> entityList) {
        List<SpectrumLicenseDTO> dtos = new ArrayList<>();
        for (SpectrumLicense entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
