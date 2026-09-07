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
@Table(name = "domain_registrations")
public class DomainRegistration extends BaseClass {
    private String domainName;
    private LocalDate registeredDate;
    private LocalDate expiryDate;
    private String status;

    @ManyToOne
    private Citizen citizen;
}
