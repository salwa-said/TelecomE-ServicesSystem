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
@Table(name = "documents")
public class Document extends BaseClass {
    private String title;
    private String type;
    private LocalDate uploadDate;

    @ManyToOne
    private Application application;
    @ManyToOne
    private Project project;
}
