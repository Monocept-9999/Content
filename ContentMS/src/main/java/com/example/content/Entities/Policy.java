package com.example.content.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



import java.time.LocalDate;

@Entity
@Data
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @NotBlank(message = "Policy Number is mandatory")
    @Size(min = 2, max = 50, message = "Policy Number should be between 2 and 50 characters")
    private String policyNumber;

    // @NotBlank(message = "Policy Type is mandatory")
    // private String policyType;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;
}
