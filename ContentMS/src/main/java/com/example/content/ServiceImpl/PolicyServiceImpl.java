package com.example.content.ServiceImpl;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyStatus;
import com.example.content.Repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.content.Entities.PolicyType;
import com.example.content.Repository.PolicyTypeRepo;
import com.example.content.Service.PolicyService;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

	Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Autowired
	PolicyTypeRepo policyTypeRepo;

	@Autowired
	private PolicyRepository policyRepository;


	@Override
	public List<Policy> getExpiringSoonPolicies() {
		return policyRepository.findAllByStatus(PolicyStatus.EXPIRING_SOON);
	}

	@Override
	public List<Policy> getExpiredPolicies() {
		return policyRepository.findAllByStatus(PolicyStatus.EXPIRED);
	}

	@Override
	public List<Policy> getActivePolicies() {
		return policyRepository.findAllByStatus(PolicyStatus.ACTIVE);
	}

	@Override
	public Policy createPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public String deletePolicy(Long policyId) {
		policyRepository.deleteById(policyId);
		return "Policy Deleted Successfully with ID : " + policyId;
	}

	/// this method will update policy status every day at midnight
	@Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
	public void updatePolicyStatuses() {
		List<Policy> policies = policyRepository.findAll();
		LocalDate today = LocalDate.now();

		for (Policy policy : policies) {
			if (policy.getEndDate().isBefore(today)) {
				policy.setStatus(PolicyStatus.EXPIRED);
			} else if (policy.getEndDate().isEqual(today.plusDays(30))) {
				policy.setStatus(PolicyStatus.EXPIRING_SOON);
			} else {
				policy.setStatus(PolicyStatus.ACTIVE);
			}
		}

		policyRepository.saveAll(policies);
	}

	@Override
	public boolean savePolicyType(int id, String name) {

		if (id > 0 && !name.isEmpty()) {

			try {

				PolicyType policyTypeEntity = new PolicyType();
				policyTypeEntity.setId(id);
				policyTypeEntity.setName(name);
				policyTypeRepo.save(policyTypeEntity);

				return true;
			} catch (Exception e) {
				logger.error("Exception in saving class " + e);
			}

		}

		return false;

	}

}
