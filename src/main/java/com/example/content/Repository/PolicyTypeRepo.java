package com.example.content.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.content.Entities.PolicyType;

@Repository
public interface PolicyTypeRepo extends CrudRepository<PolicyType,Long>{
	
	

}
