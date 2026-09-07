package com.example.TelecomE_ServicesSystem.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "ministries")

public class Ministry extends BaseClass {
    private String name;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "ministry", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "ministry", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();
}

