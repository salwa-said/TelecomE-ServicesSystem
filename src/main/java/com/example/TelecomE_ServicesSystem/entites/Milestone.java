package com.example.TelecomE_ServicesSystem.entites;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "milestones")
public class Milestone  extends BaseClass {
    private String title;
    private LocalDate dueDate;
    private String status;

    @ManyToOne
    private Project project;
}
