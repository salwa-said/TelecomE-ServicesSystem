package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "officers")
public class Officer extends BaseClass {
    private String name;
    private String email;
    private String phoneNumber;
    private String designation;

    @ManyToOne
    private Department department;
    @JsonIgnore
    @OneToMany(mappedBy = "officer", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "officer", cascade = CascadeType.ALL)
    private List<Inspection> inspections = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "officer", cascade = CascadeType.ALL)
    private List<Complaint> complaints = new ArrayList<>();
}
