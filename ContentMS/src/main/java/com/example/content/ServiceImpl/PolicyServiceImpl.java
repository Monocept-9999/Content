package com.example.content.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.content.Entities.PolicyMetaData;
import com.example.content.Entities.PolicyType;
import com.example.content.Repository.PolicyMetaTypeRepo;
import com.example.content.Repository.PolicyTypeRepo;
import com.example.content.Service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Autowired
	PolicyTypeRepo policyTypeRepo;

    @Autowired
    PolicyMetaTypeRepo policyMetaTypeRepo;




	@Override
	public boolean savePolicyType(Long id, String name) {

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
    public PolicyMetaData createPolicy(PolicyMetaData policy) {
        return policyMetaTypeRepo.save(policy);
    }

    @Override
    public String deletePolicy(Long id) {
       policyMetaTypeRepo.deleteById(id);
        return "Policy Deleted Successfully";
    }


    @Override
    public List<PolicyMetaData> getAllPolicies() {
        Iterable<PolicyMetaData> data = policyMetaTypeRepo.findAll();
        List<PolicyMetaData> policies=new ArrayList<>();
        data.forEach(policies::add);
        return policies;
    }



    @Override
    public Optional<PolicyMetaData> getPolicyById(Long id) {
        return policyMetaTypeRepo.findById(id);
    }



    @Override
    public PolicyMetaData update_policy(Long id, PolicyMetaData policy) {
        Optional<PolicyMetaData>temp=policyMetaTypeRepo.findById(id);
        if(temp.isPresent()) 
        {
            PolicyMetaData existingPolicy=temp.get();
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
            existingPolicy.setCreatedAt(policy.getCreatedAt());
            existingPolicy.setEndDate(policy.getEndDate());
            return policyMetaTypeRepo.save(existingPolicy);
        }
        else
        {
            return null;
        }
}



    @Override
    public float check_EMI(Long id) {
       Optional<PolicyMetaData>data =policyMetaTypeRepo.findById(id);

       if(data.isPresent())
       {
        PolicyMetaData policy = data.get();
        float amount = policy. getAmount();
        int year= policy.getTenure();

        float emi =amount/(year*12);
        return emi;
       }
       else
       {
        return 0;
       }
    }

}
