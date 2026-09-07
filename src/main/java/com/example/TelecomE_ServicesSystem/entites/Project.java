package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseClass {
    private String title;
    private BigDecimal budget;
    private LocalDate startDate;
    private String status;

    @ManyToOne
    private Ministry ministry;
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Milestone> milestones = new ArrayList<>();
    @ManyToOne
    private Vendor vendor;
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();
}

