package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Service;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    @Positive(message = "ID must be positive")
    private Long serviceId;
    @NotBlank(message = "Service name cannot be blank")
    @Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters")
    private String name;
    @Size(max = 255, message = "Service description cannot exceed 255 characters")
    private String description;
    @Positive(message = "Fee must be positive")
    private BigDecimal fee;
    @Positive(message = "Processing days must be positive")
    private Integer processingDays;
    @NotNull(message = "Related ID is required")
    private Long departmentId;

    public static ServiceDTO convertToDTO(Service entity) {
        if (entity == null) return null;
        return ServiceDTO.builder()
                .serviceId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .fee(entity.getFee())
                .processingDays(entity.getProcessingDays())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .build();
    }

    public static List<ServiceDTO> convertToDTO(List<Service> entityList) {
        List<ServiceDTO> dtos = new ArrayList<>();
        for (Service entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}