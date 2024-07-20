package com.example.content.Service;

import com.example.content.Entities.Policy;


import java.util.List;

public interface PolicyService {

    Policy createPolicy(Policy policy);
    String deletePolicy(Long policyId);
    List<Policy> get_All_Policy();
    Policy get_Policy_byID(Long policyId);
    Policy updatePolicy(Long  policy, Policy newdata);

}
