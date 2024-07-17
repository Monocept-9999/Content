package com.example.content.Service;

import com.example.content.Entities.Policy;

import com.example.content.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;


    @Override
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public String deletePolicy(Long policyId) {
        policyRepository.deleteById(policyId);
        return "Policy Deleted Successfully";
    }


        
    }
