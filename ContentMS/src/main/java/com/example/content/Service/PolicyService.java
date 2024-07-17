package com.example.content.Service;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyStatus;

import java.util.List;

public interface PolicyService {

    List<Policy> getExpiringSoonPolicies();
    List<Policy> getExpiredPolicies();
    List<Policy> getActivePolicies();
    Policy createPolicy(Policy policy);
    String deletePolicy(Long policyId);
}
