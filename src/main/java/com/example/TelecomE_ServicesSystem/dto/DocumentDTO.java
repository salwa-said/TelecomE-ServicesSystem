package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Document;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    @Positive(message = "ID must be positive")
    private Long documentId;
    @NotBlank(message = "Document title cannot be blank")
    @Size(min = 2, max = 150, message = "Document title must be between 2 and 150 characters")
    private String title;
    @NotBlank(message = "Document type cannot be blank")
    @Size(max = 50, message = "Document type cannot exceed 50 characters")
    private String type;
    @NotNull(message = "Upload date is required")
    @PastOrPresent(message = "Upload date cannot be in the future")
    private LocalDate uploadDate;
    private Long applicationId;
    private Long projectId;

    public static DocumentDTO convertToDTO(Document entity) {
        if (entity == null) return null;
        return DocumentDTO.builder()
                .documentId(entity.getId())
                .title(entity.getTitle())
                .type(entity.getType())
                .uploadDate(entity.getUploadDate())
                .applicationId(entity.getApplication() != null ? entity.getApplication().getId() : null)
                .projectId(entity.getProject() != null ? entity.getProject().getId() : null)
                .build();
    }

    public static List<DocumentDTO> convertToDTO(List<Document> entityList) {
        List<DocumentDTO> dtos = new ArrayList<>();
        for (Document entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
