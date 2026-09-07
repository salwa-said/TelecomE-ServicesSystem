package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Vendor;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    @Positive(message = "ID must be positive")
    private Long vendorId;
    @NotBlank(message = "Vendor name cannot be blank")
    @Size(min = 2, max = 100, message = "Vendor name must be between 2 and 100 characters")
    private String name;
    @NotBlank(message = "Vendor email cannot be blank")
    @Email(message = "Vendor email is invalid")
    private String contactEmail;
    @Size(max = 30, message = "Phone number cannot exceed 30 characters")
    private String phoneNumber;
    @NotBlank(message = "Vendor country cannot be blank")
    @Size(min = 2, max = 80, message = "Country must be between 2 and 80 characters")
    private String country;

    public static VendorDTO convertToDTO(Vendor entity) {
        if (entity == null) return null;
        return VendorDTO.builder()
                .vendorId(entity.getId())
                .name(entity.getName())
                .contactEmail(entity.getContactEmail())
                .phoneNumber(entity.getPhoneNumber())
                .country(entity.getCountry())
                .build();
    }

    public static List<VendorDTO> convertToDTO(List<Vendor> entityList) {
        List<VendorDTO> dtos = new ArrayList<>();
        for (Vendor entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
