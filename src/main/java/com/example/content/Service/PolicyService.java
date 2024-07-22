package com.example.content.Service;

import java.util.Optional;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.content.Entities.PolicyMetaData;

public interface PolicyService {

	public boolean savePolicyType(Long id, String name);


	PolicyMetaData createPolicy(PolicyMetaData policy);
    String deletePolicy(Long id);
    List<PolicyMetaData> getAllPolicies();
    Optional<PolicyMetaData> getPolicyById(Long id);
    PolicyMetaData update_policy(Long id, PolicyMetaData policy);
    float check_EMI(Long id);
}
