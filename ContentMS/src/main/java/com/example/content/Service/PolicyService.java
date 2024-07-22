package com.example.content.Service;

import com.example.content.Beans.PolicyResponse;

import com.example.content.Entities.Policy;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PolicyService {

	public boolean savePolicyType(int id, String name);

	public PolicyResponse getRecommendedPolicy(int age);

	List<Policy> getExpiringSoonPolicies();

	List<Policy> getExpiredPolicies();

	List<Policy> getActivePolicies();

	Policy createPolicy(Policy policy);

	String deletePolicy(Long policyId);

}
