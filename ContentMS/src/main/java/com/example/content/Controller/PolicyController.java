package com.example.content.Controller;

import com.example.content.Entities.Policy;
import com.example.content.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/active")
    public ResponseEntity<List<Policy>> getActivePolicies(){
        return new ResponseEntity<List<Policy>>(policyService.getActivePolicies(), HttpStatus.OK);
    }

    @GetMapping("/expiring-soon")
    public ResponseEntity<List<Policy>> getExpiringSoonPolicies(){
        return new ResponseEntity<List<Policy>>(policyService.getExpiringSoonPolicies(), HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Policy>> getExpiredPolicies(){
        return new ResponseEntity<List<Policy>>(policyService.getExpiredPolicies(), HttpStatus.OK);
    }

    @PostMapping("/create-policy")
    public  ResponseEntity<Policy> createPolicy(@RequestBody Policy  policy){
        System.out.println("hellloooo");
        return new ResponseEntity<Policy>(policyService.createPolicy(policy), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-policy")
    public ResponseEntity<String> deletePolicy(@PathVariable("policyId") Long policyId){
        return new ResponseEntity<String>(policyService.deletePolicy(policyId), HttpStatus.OK);
    }
}
