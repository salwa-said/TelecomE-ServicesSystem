package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "citizens")
public class Citizen extends BaseClass {
    private String name;
    private String nationalId;
    private String phoneNumber;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<Complaint> complaints = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<DomainRegistration> domainRegistrations = new ArrayList<>();
}
