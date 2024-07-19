package com.example.content.Service;

import com.example.content.Beans.PolicyResponse;

public interface PolicyService {

	public boolean savePolicyType(int id, String name);

	public PolicyResponse getRecommendedPolicy(int age);

}
