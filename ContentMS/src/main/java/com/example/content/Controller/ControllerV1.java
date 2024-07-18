package com.example.content.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.content.Beans.PolicyResponse;
import com.example.content.ServiceImpl.PolicyServiceImpl;

@RestController
public class ControllerV1 {

	Logger logger = LoggerFactory.getLogger(ControllerV1.class);

	@Autowired
	PolicyServiceImpl policyServiceImpl;

	@PostMapping(value = "/savePolicyType")
	public PolicyResponse savePolicyType(@RequestParam int id, @RequestParam String name) {

		PolicyResponse policyResponse = new PolicyResponse();

		try {

			if (policyServiceImpl.savePolicyType(id, name)) {

				policyResponse.setData(id + " " + name);
				policyResponse.setMessage("Data saved succesfully");
				policyResponse.setStatus(true);
			}

			return policyResponse;
		} catch (Exception e) {

			logger.error("Excepion--......" + e);
			policyResponse.setMessage("Failed to save Data");
			policyResponse.setStatus(false);

		}

		return policyResponse;

	}

	@GetMapping(value = "/getRecommendedPolicy")
	public PolicyResponse getRecommendedPolicy(@RequestParam int age) {

		PolicyResponse policyResponse = new PolicyResponse();

		try {
			policyResponse = policyServiceImpl.getRecommendedPolicy(age);
		} catch (Exception e) {
			logger.error("Excepion--......" + e);
		}

		return policyResponse;

	}

	@GetMapping(value = "/getConditionalPolicy")
	public PolicyResponse getConditionalPolicy(@RequestParam int age, @RequestParam boolean isSmoker,
			@RequestParam int income) {

		PolicyResponse policyResponse = new PolicyResponse();

		try {
			policyResponse = policyServiceImpl.getConditionalPolicyPackage(age, isSmoker, income);
		} catch (Exception e) {
			logger.error("Excepion--......" + e);
		}
		return policyResponse;

	}
}