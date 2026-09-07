package com.example.TelecomE_ServicesSystem.entites;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "inspections")
public class Inspection extends BaseClass {
    private LocalDate inspectionDate;
    private String result;
    private String notes;

    @ManyToOne
    private Operator operator;
    @ManyToOne
    private Officer officer;
}