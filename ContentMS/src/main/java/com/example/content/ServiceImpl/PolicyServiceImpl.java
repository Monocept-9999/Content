package com.example.content.ServiceImpl;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyStatus;
import com.example.content.Repository.PolicyRepository;
import com.example.content.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

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
}
