package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Department;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    @Positive(message = "ID must be positive")
    private Long departmentId;
    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;
    @Size(max = 255, message = "Department description cannot exceed 255 characters")
    private String description;
    @NotNull(message = "Related ID is required")
    private Long ministryId;

    public static DepartmentDTO convertToDTO(Department entity) {
        if (entity == null) return null;
        return DepartmentDTO.builder()
                .departmentId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .ministryId(entity.getMinistry() != null ? entity.getMinistry().getId() : null)
                .build();
    }

    public static List<DepartmentDTO> convertToDTO(List<Department> entityList) {
        List<DepartmentDTO> dtos = new ArrayList<>();
        for (Department entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

