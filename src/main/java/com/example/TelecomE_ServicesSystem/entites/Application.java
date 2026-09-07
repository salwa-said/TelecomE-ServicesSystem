package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "applications")

public class Application extends BaseClass {
    private LocalDate applicationDate;
    private String status;
    private String referenceNumber;

    @ManyToOne
    private Citizen citizen;
    @ManyToOne
    private Service service;
    @ManyToOne
    private Officer officer;
    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();
}