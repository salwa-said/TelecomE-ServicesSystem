package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Officer;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficerDTO {
    @Positive(message = "ID must be positive")
    private Long officerId;
    @NotBlank(message = "Officer name cannot be blank")
    @Size(min = 3, max = 100, message = "Officer name must be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "Officer email cannot be blank")
    @Email(message = "Officer email is invalid")
    private String email;
    @Size(max = 30, message = "Phone number cannot exceed 30 characters")
    private String phoneNumber;
    @NotBlank(message = "Designation cannot be blank")
    @Size(min = 2, max = 100, message = "Designation must be between 2 and 100 characters")
    private String designation;
    @NotNull(message = "Related ID is required")
    private Long departmentId;

    public static OfficerDTO convertToDTO(Officer entity) {
        if (entity == null) return null;
        return OfficerDTO.builder()
                .officerId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .designation(entity.getDesignation())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .build();
    }

    public static List<OfficerDTO> convertToDTO(List<Officer> entityList) {
        List<OfficerDTO> dtos = new ArrayList<>();
        for (Officer entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

