package com.example.content.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.content.Entities.PolicyType;
import com.example.content.Repository.PolicyTypeRepo;
import com.example.content.Service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Autowired
	PolicyTypeRepo policyTypeRepo;

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
