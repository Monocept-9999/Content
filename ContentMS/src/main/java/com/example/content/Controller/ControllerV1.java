package com.example.content.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.content.Beans.PolicyResponse;
import com.example.content.Entities.PolicyMetaData;
import com.example.content.Service.PolicyService;
import com.example.content.ServiceImpl.PolicyServiceImpl;

@RestController
@RequestMapping("/policy")
public class ControllerV1 {

	Logger logger = LoggerFactory.getLogger(ControllerV1.class);

	@Autowired
	PolicyServiceImpl policyServiceImpl;
	 @Autowired
    private PolicyService policyService;
	
	@PostMapping(value = "/savePolicyType")
	public PolicyResponse savePolicyType(@RequestParam int id, @RequestParam String name) {

		PolicyResponse policyResponse = new PolicyResponse();

		try {
            Long idLong = Long.valueOf(id);

			if (policyServiceImpl.savePolicyType(idLong, name)) {

				policyResponse.setData(id + " " + name);
				policyResponse.setMessage("Data saved succesfully");
				policyResponse.setStatus(true);
			}

			return policyResponse;
		} catch (Exception e) {

			logger.error("Excepion--......" + e);
			policyResponse.setMessage("Failed to save Data");
			policyResponse.setStatus(false);

		}

		return policyResponse;

	}




	
    // Creating  a policy 

    @PostMapping("/create_policy")
    public  ResponseEntity<PolicyMetaData> createPolicy(@RequestBody PolicyMetaData  policy){
        return new ResponseEntity<PolicyMetaData>(policyService.createPolicy(policy), HttpStatus.CREATED);
    }

    // Get all policy from the database
    @GetMapping("/get_all")
    public ResponseEntity<List<PolicyMetaData>>get_All_Policies()
    {
        List<PolicyMetaData> policies = policyService.getAllPolicies();
        return new ResponseEntity<List<PolicyMetaData>>(policies, HttpStatus.OK);
    }



    
    // Get particular policy from the database with the help of id 
    @GetMapping("/get_byId/{id}")
    public ResponseEntity<Optional<PolicyMetaData>>get_Policy_ById(@PathVariable Long id)
    {
       Optional<PolicyMetaData> policies = policyService.getPolicyById(id);
       if (policies.isPresent())
       return  new ResponseEntity<>(policies,HttpStatus.OK);
       else
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    // Updating a policy in the database with the help of id
    @PutMapping("/update_policy/{id}")
    public ResponseEntity<PolicyMetaData>update_policy(@PathVariable Long id, @RequestBody PolicyMetaData policy)
    {
        policyService.update_policy(id, policy);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }


    // Delete a policy in the database with the help of id
    @DeleteMapping("/delete_policy/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable Long id ){
        return new ResponseEntity<String>(policyService.deletePolicy(id), HttpStatus.OK);
    }


    @GetMapping("/get_emi/{id}")
    public float get_Emi(@PathVariable Long id)
    {
        return policyService.check_EMI(id);
    }

}
