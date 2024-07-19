package com.example.content.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.content.Beans.PolicyResponse;
import com.example.content.Entities.PolicyPackages;
import com.example.content.Entities.PolicyType;
import com.example.content.Enums.PolicyPackageEnum;
import com.example.content.Repository.PolicyPackagesRepo;
import com.example.content.Repository.PolicyTypeRepo;
import com.example.content.Service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Autowired
	PolicyTypeRepo policyTypeRepo;

	@Autowired
	PolicyPackagesRepo policyPackagesRepo;

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
