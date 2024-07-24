package com.example.content.Controller;

import com.example.content.Entities.Policy;
import com.example.content.Entities.PolicyMetaData;
import com.example.content.Service.PolicyService;
import com.example.content.ServiceImpl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/policy")
public class policy_Crud {

    @Autowired
    PolicyServiceImpl policyServiceImpl;
    @Autowired
    private PolicyService policyService;

    // Creating the Policy Controller
    @PostMapping("/create_policy")
    public ResponseEntity<PolicyMetaData> createPolicy(@RequestBody PolicyMetaData  policy){
        return new ResponseEntity<PolicyMetaData>(policyService.createPolicy (policy), HttpStatus.CREATED);
    }


    // Get all policy from the database
    @GetMapping("/get_all")
    public ResponseEntity<List<PolicyMetaData>>get_All_Policies()
    {
        List<PolicyMetaData> policies =policyService .getAllPolicies();
        return new ResponseEntity<List<PolicyMetaData>>(policies, HttpStatus.OK);
    }




    // Get particular policy from the database with the help of id
    @GetMapping("/get_byId/{id}")
    public ResponseEntity<Optional<PolicyMetaData>>get_Policy_ById(@PathVariable int id)
    {
        Optional<PolicyMetaData> policies = policyService.getPolicyById(id);
        if(policies.isPresent())
            return new ResponseEntity<>(policies, HttpStatus.OK);
        else {
            System.out.println("Id is not present in the database please recheck once");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    // Updating a policy in the database with the help of id
    @PutMapping("/update_policy/{id}")
    public ResponseEntity<PolicyMetaData>update_policy(@PathVariable int id, @RequestBody PolicyMetaData policy)
    {
        PolicyMetaData data= policyService.update_policy(id, policy);
        if(data!=null)
        return new ResponseEntity<>(policy, HttpStatus.OK);
        else
        {
            System.out.println("Id is not present in the database please recheck once");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete a policy in the database with the help of id
    @DeleteMapping("/delete_policy/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable int id ){
        return new ResponseEntity<String>(policyService.deletePolicy(id), HttpStatus.OK);
    }

//    @GetMapping("/get_emi/{id}")
//    public float get_Emi(@PathVariable int id)
//    {
//        return policyService.check_EMI(id);
//    }

}
