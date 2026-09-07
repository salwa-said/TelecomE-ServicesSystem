package com.example.TelecomE_ServicesSystem.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "vendors")
public class Vendor extends BaseClass {
    private String name;
    private String contactEmail;
    private String phoneNumber;
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();
}