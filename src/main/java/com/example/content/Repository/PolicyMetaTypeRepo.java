package com.example.content.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.content.Entities.PolicyMetaData;

@Repository
public interface PolicyMetaTypeRepo extends CrudRepository<PolicyMetaData,Long> {

}
