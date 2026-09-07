package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Inspection;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionDTO {
    @Positive(message = "ID must be positive")
    private Long inspectionId;
    @NotNull(message = "Inspection date is required")
    @FutureOrPresent(message = "Inspection date cannot be in the past")
    private LocalDate inspectionDate;
    @Size(max = 100, message = "Inspection result cannot exceed 100 characters")
    private String result;
    @Size(max = 500, message = "Inspection notes cannot exceed 500 characters")
    private String notes;
    @NotNull(message = "Related ID is required")
    private Long operatorId;
    @NotNull(message = "Related ID is required")
    private Long officerId;

    public static InspectionDTO convertToDTO(Inspection entity) {
        if (entity == null) return null;
        return InspectionDTO.builder()
                .inspectionId(entity.getId())
                .inspectionDate(entity.getInspectionDate())
                .result(entity.getResult())
                .notes(entity.getNotes())
                .operatorId(entity.getOperator() != null ? entity.getOperator().getId() : null)
                .officerId(entity.getOfficer() != null ? entity.getOfficer().getId() : null)
                .build();
    }

    public static List<InspectionDTO> convertToDTO(List<Inspection> entityList) {
        List<InspectionDTO> dtos = new ArrayList<>();
        for (Inspection entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

