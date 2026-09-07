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
@Table(name = "payments")
public class Payment extends BaseClass {
    private BigDecimal amount;
    private String method;
    private String status;
    private LocalDate paidDate;

    @OneToOne
    @JoinColumn(name = "application_id", unique = true)
    private Application application;
}

