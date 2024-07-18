package com.example.content.Repository;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findAllByStatus(PolicyStatus status);
}
