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

    @PostMapping("/create-policy")
    public  ResponseEntity<Policy> createPolicy(@RequestBody Policy  policy){
        return new ResponseEntity<Policy>(policyService.createPolicy(policy), HttpStatus.CREATED);
    }

    @GetMapping("/all-policies")
    public ResponseEntity<List<Policy>> all_Policies()
    {
        List<Policy> policyList = policyService.get_All_Policy();
        if(!policyList.isEmpty())
        {
            return new ResponseEntity<>(policyList, HttpStatus.OK);
        }
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    @GetMapping("/get-policy/{id}")
    public Policy getPolicy_ById(@PathVariable Long id)
    {
        return policyService.get_Policy_byID(id);
    }

    @PutMapping("/update-policy/{id}")
    public ResponseEntity<Policy> update_Policy( @PathVariable Long id,@RequestBody Policy policy)
    {
        Policy temp=policyService.updatePolicy(id,policy);
        if(temp!=null)
        {
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete-policy")
    public ResponseEntity<String> deletePolicy(@PathVariable("policyId") Long policyId){
        return new ResponseEntity<String>(policyService.deletePolicy(policyId), HttpStatus.OK);
    }
}
