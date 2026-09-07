package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Service extends BaseClass {
    private String name;
    private String description;
    private BigDecimal fee;
    private Integer processingDays;

    @ManyToOne
    private Department department;
    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
}
