package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    @Positive(message = "ID must be positive")
    private Long applicationId;
    @NotNull(message = "Application date is required")
    @PastOrPresent(message = "Application date cannot be in the future")
    private LocalDate applicationDate;
    @NotBlank(message = "Application status cannot be blank")
    @Size(max = 30, message = "Status cannot exceed 30 characters")
    private String status;
    @Size(max = 50, message = "Reference number cannot exceed 50 characters")
    private String referenceNumber;
    @NotNull(message = "Related ID is required")
    private Long citizenId;
    @NotNull(message = "Related ID is required")
    private Long serviceId;
    private Long officerId;
    private Long paymentId;

    public static ApplicationDTO convertToDTO(Application entity) {
        if (entity == null) return null;
        return ApplicationDTO.builder()
                .applicationId(entity.getId())
                .applicationDate(entity.getApplicationDate())
                .status(entity.getStatus())
                .referenceNumber(entity.getReferenceNumber())
                .citizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : null)
                .serviceId(entity.getService() != null ? entity.getService().getId() : null)
                .officerId(entity.getOfficer() != null ? entity.getOfficer().getId() : null)
                .paymentId(entity.getPayment() != null ? entity.getPayment().getId() : null)
                .build();
    }

    public static List<ApplicationDTO> convertToDTO(List<Application> entityList) {
        List<ApplicationDTO> dtos = new ArrayList<>();
        for (Application entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
