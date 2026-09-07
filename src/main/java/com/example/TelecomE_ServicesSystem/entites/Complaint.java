package com.example.TelecomE_ServicesSystem.entites;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "complaints")
public class Complaint extends BaseClass {
    private String subject;
    private String description;
    private String status;
    private LocalDate filedDate;

    @ManyToOne
    private Citizen citizen;
    @ManyToOne
    private Operator operator;
    @ManyToOne
    private Officer officer;
}
