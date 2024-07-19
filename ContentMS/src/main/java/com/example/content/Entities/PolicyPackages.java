package com.example.content.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "policy_packages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyPackages {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "package_Name")
	private String packageName;

	@Column(name = "lowerAgeLimit")
	private int lowerAgeLimit;

	@Column(name = "upperAgeLimit")
	private int upperAgeLimit;

	@Column(name = "tenure")
	private int tenure;

	@Column(name = "premium")
	private int premium;

	@Column(name = "orgName")
	private String orgName;

	@Column(name = "availableHospitalIds")
	private List<Integer> availableHospitalIds;

	@Column(name = "EMIAvailable")
	private boolean EMIAvailable;

	@Column(name = "EMIAmount")
	private int EMIAmount;

}
