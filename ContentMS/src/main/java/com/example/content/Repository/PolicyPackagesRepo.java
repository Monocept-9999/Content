package com.example.content.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.content.Entities.PolicyPackages;

@Repository
public interface PolicyPackagesRepo extends CrudRepository<PolicyPackages, Integer> {

	@Query(nativeQuery = true, value = "Select * from policy_packages p where p.package_Name = :pkgName")
	public PolicyPackages getPolicyPackage(String pkgName);
}
