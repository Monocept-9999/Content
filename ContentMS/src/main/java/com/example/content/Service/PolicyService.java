package com.example.content.Service;

import com.example.content.Beans.PolicyResponse;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyMetaData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PolicyService {

	public boolean savePolicyType(int id, String name);

	public PolicyResponse getRecommendedPolicy(int age);

	List<Policy> getExpiringSoonPolicies();

	List<Policy> getExpiredPolicies();

	List<Policy> getActivePolicies();


	PolicyMetaData createPolicy(PolicyMetaData policy);

	String deletePolicy(int id);

	List<PolicyMetaData> getAllPolicies();

	Optional<PolicyMetaData> getPolicyById(Long id);

	PolicyMetaData update_policy(int id, PolicyMetaData policy);

	Optional<PolicyMetaData> getPolicyById(int id);

	float check_EMI(int id);
}
