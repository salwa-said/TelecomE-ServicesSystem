package com.example.TelecomE_ServicesSystem.dto;
import com.example.TelecomE_ServicesSystem.entites.Payment;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @Positive(message = "ID must be positive")
    private Long paymentId;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
    @NotBlank(message = "Payment method cannot be blank")
    @Size(max = 30, message = "Payment method cannot exceed 30 characters")
    private String method;
    @NotBlank(message = "Payment status cannot be blank")
    @Size(max = 30, message = "Payment status cannot exceed 30 characters")
    private String status;
    private LocalDate paidDate;
    @NotNull(message = "Related ID is required")
    private Long applicationId;

    public static PaymentDTO convertToDTO(Payment entity) {
        if (entity == null) return null;
        return PaymentDTO.builder()
                .paymentId(entity.getId())
                .amount(entity.getAmount())
                .method(entity.getMethod())
                .status(entity.getStatus())
                .paidDate(entity.getPaidDate())
                .applicationId(entity.getApplication() != null ? entity.getApplication().getId() : null)
                .build();
    }

    public static List<PaymentDTO> convertToDTO(List<Payment> entityList) {
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment entity : entityList) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}