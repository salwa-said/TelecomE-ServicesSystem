package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Project;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    @Positive(message = "ID must be positive")
    private Long projectId;
    @NotBlank(message = "Project title cannot be blank")
    @Size(min = 3, max = 150, message = "Project title must be between 3 and 150 characters")
    private String title;
    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be positive")
    private BigDecimal budget;
    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;
    @NotBlank(message = "Project status cannot be blank")
    @Size(max = 30, message = "Project status cannot exceed 30 characters")
    private String status;
    @NotNull(message = "Related ID is required")
    private Long ministryId;
    private Long vendorId;

    public static ProjectDTO convertToDTO(Project entity) {
        if (entity == null) return null;
        return ProjectDTO.builder()
                .projectId(entity.getId())
                .title(entity.getTitle())
                .budget(entity.getBudget())
                .startDate(entity.getStartDate())
                .status(entity.getStatus())
                .ministryId(entity.getMinistry() != null ? entity.getMinistry().getId() : null)
                .vendorId(entity.getVendor() != null ? entity.getVendor().getId() : null)
                .build();
    }

    public static List<ProjectDTO> convertToDTO(List<Project> entityList) {
        List<ProjectDTO> dtos = new ArrayList<>();
        for (Project entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
