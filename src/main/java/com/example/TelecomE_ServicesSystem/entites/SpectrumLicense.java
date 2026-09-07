package com.example.TelecomE_ServicesSystem.entites;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "spectrum_licenses")
public class SpectrumLicense extends BaseClass {
    private String bandName;
    private BigDecimal frequencyMhz;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String status;

    @ManyToOne
    private Operator operator;
}
