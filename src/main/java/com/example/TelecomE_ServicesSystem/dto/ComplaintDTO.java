package com.example.TelecomE_ServicesSystem.dto;

import com.example.TelecomE_ServicesSystem.entites.Complaint;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {
    @Positive(message = "ID must be positive")
    private Long complaintId;
    @NotBlank(message = "Complaint subject cannot be blank")
    @Size(min = 3, max = 150, message = "Subject must be between 3 and 150 characters")
    private String subject;
    @NotBlank(message = "Complaint description cannot be blank")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;
    @NotBlank(message = "Complaint status cannot be blank")
    @Size(max = 30, message = "Status cannot exceed 30 characters")
    private String status;
    @NotNull(message = "Filed date is required")
    @PastOrPresent(message = "Filed date cannot be in the future")
    private LocalDate filedDate;
    @NotNull(message = "Related ID is required")
    private Long citizenId;
    @NotNull(message = "Related ID is required")
    private Long operatorId;
    private Long officerId;

    public static ComplaintDTO convertToDTO(Complaint entity) {
        if (entity == null) return null;
        return ComplaintDTO.builder()
                .complaintId(entity.getId())
                .subject(entity.getSubject())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .filedDate(entity.getFiledDate())
                .citizenId(entity.getCitizen() != null ? entity.getCitizen().getId() : null)
                .operatorId(entity.getOperator() != null ? entity.getOperator().getId() : null)
                .officerId(entity.getOfficer() != null ? entity.getOfficer().getId() : null)
                .build();
    }

    public static List<ComplaintDTO> convertToDTO(List<Complaint> entityList) {
        List<ComplaintDTO> dtos = new ArrayList<>();
        for (Complaint entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}

