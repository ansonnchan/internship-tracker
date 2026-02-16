package com.anson.internshiptracker.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Data
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @NotBlank(message = "Company name is required")
    private String company;
    
    @NotBlank(message = "Position is required")
    private String position;
    
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    private LocalDate dateApplied;
    
    // allow many applications belong to one user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
