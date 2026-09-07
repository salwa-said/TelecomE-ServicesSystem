package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Milestone;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDTO {
    @Positive(message = "ID must be positive")
    private Long milestoneId;
    @NotBlank(message = "Milestone title cannot be blank")
    @Size(min = 3, max = 150, message = "Milestone title must be between 3 and 150 characters")
    private String title;
    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
    @NotBlank(message = "Milestone status cannot be blank")
    @Size(max = 30, message = "Milestone status cannot exceed 30 characters")
    private String status;
    @NotNull(message = "Related ID is required")
    private Long projectId;

    public static MilestoneDTO convertToDTO(Milestone entity) {
        if (entity == null) return null;
        return MilestoneDTO.builder()
                .milestoneId(entity.getId())
                .title(entity.getTitle())
                .dueDate(entity.getDueDate())
                .status(entity.getStatus())
                .projectId(entity.getProject() != null ? entity.getProject().getId() : null)
                .build();
    }

    public static List<MilestoneDTO> convertToDTO(List<Milestone> entityList) {
        List<MilestoneDTO> dtos = new ArrayList<>();
        for (Milestone entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

