package com.example.content.Service;

import com.example.content.Entities.Policy;


import java.util.List;

public interface PolicyService {

    Policy createPolicy(Policy policy);
    String deletePolicy(Long policyId);
}
