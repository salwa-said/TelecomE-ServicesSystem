package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "operators")
public class Operator extends BaseClass {
    private String name;
    private String licenseNumber;
    private String contactEmail;
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
    private List<SpectrumLicense> spectrumLicenses = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
    private List<Inspection> inspections = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
    private List<Complaint> complaints = new ArrayList<>();
}

