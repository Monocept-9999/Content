package com.example.content.Entities;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Policy_Meta_Data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  policyId;

	private int userId;
	@NotBlank(message = "Policy Number is mandatory")
	@Size(min = 2, max = 50, message = "Policy Number should be between 2 and 50 characters")
	private String policyNumber;

	private int amount;

	private int tenure;

	private LocalDateTime createdAt;

	private LocalDateTime endDate;

	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
