package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department extends BaseClass {
    private String name;
    private String description;

    @ManyToOne
    private Ministry ministry;
    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Officer> officers = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Service> services = new ArrayList<>();
}
