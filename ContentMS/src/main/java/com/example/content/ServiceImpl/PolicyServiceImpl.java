package com.example.content.ServiceImpl;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyStatus;
import com.example.content.Repository.PolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.content.Beans.PolicyResponse;
import com.example.content.Entities.PolicyPackages;
import com.example.content.Entities.PolicyType;
import com.example.content.Enums.PolicyPackageEnum;
import com.example.content.Repository.PolicyPackagesRepo;
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
	PolicyPackagesRepo policyPackagesRepo;

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
/*	@Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
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
		}*/

//		policyRepository.saveAll(policies);

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

	@Override
	public PolicyResponse getRecommendedPolicy(int age) {

		PolicyResponse policyResponse = new PolicyResponse();
		String pkgName = "";

		if (age >= 20 && age <= 40) {
			pkgName = PolicyPackageEnum.PLATINUM.toString();
		} else if (age > 40 && age <= 50) {
			pkgName = PolicyPackageEnum.GOLD.toString();

		} else if (age > 50 && age < 65) {
			pkgName = PolicyPackageEnum.SILVER.toString();
		} else {
			pkgName = PolicyPackageEnum.BRONZE.toString();
		}

		if (!pkgName.isBlank() && !pkgName.isEmpty()) {

			PolicyPackages pObj = policyPackagesRepo.getPolicyPackage(pkgName);
			policyResponse.setData(pObj);

		}

		if (policyResponse.getData() != null) {
			policyResponse.setMessage("Data fetched successfully");
			policyResponse.setStatus(true);
		} else {
			policyResponse.setMessage("Failed to fetch Data");
			policyResponse.setStatus(false);
		}

		return policyResponse;

	}

	public String getPackageName(int age) {

		String pkgName = "";

		if (age >= 20 && age <= 40) {
			pkgName = PolicyPackageEnum.PLATINUM.toString();
		} else if (age > 40 && age <= 50) {
			pkgName = PolicyPackageEnum.GOLD.toString();
		} else if (age > 50 && age < 65) {
			pkgName = PolicyPackageEnum.SILVER.toString();
		} else {
			pkgName = PolicyPackageEnum.BRONZE.toString();
		}

		return pkgName;

	}

	public PolicyResponse getConditionalPolicyPackage(int age, boolean isSmoker, int income) {

		PolicyResponse policyResponse = new PolicyResponse();

		String pkgName = getPackageName(age);
		PolicyPackages policyPackage = null;

		if (!pkgName.isBlank() || !pkgName.isEmpty()) {
			policyPackage = policyPackagesRepo.getPolicyPackage(pkgName);
		}

		if (income < 500000) {

			if (isSmoker) {

				policyPackage.setPremium((int) (policyPackage.getPremium() + (policyPackage.getPremium() * 0.1)));
			} else {
				policyPackage.setPremium((int) (policyPackage.getPremium() + (policyPackage.getPremium() * 0.08)));
			}
		} else {
			if (isSmoker) {

				policyPackage.setPremium((int) (policyPackage.getPremium() + (policyPackage.getPremium() * 0.05)));
			}
		}

		policyResponse.setData(policyPackage);
		return policyResponse;

	}

}
