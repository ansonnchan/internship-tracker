package com.anson.internshiptracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String company;
    private String position;
    private String status; // APPLIED, INTERVIEW, OFFER, REJECTED, OA
    private LocalDate dateApplied;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user; 
}
