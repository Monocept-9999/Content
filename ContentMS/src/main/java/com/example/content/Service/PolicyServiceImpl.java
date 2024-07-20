package com.example.content.Service;

import com.example.content.Entities.Policy;

import com.example.content.Repository.PolicyRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Policy> get_All_Policy() {
       return policyRepository.findAll();
    }


    @Override
    public Policy get_Policy_byID(Long policyId) {
        Optional<Policy>data= policyRepository.findById(policyId);
        if(data.isPresent())
        {
            return data.get();
        }
        else
        {
            return null;
        }
     }

    @Override
    public Policy updatePolicy(Long id,Policy data) {
         Policy temp=policyRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found "));
         if(temp.getPolicyNumber() != data.getPolicyNumber() &&  temp.getPolicyNumber()!=null)
         {
            temp.setPolicyNumber(data.getPolicyNumber());
            temp.setStartDate(data.getStartDate());
            temp.setEndDate(data.getEndDate());
            return policyRepository.save(temp);
         }
         else 
         {
            return null;
         }

        }


        
    }
