package com.gsg.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gsg.mongo.model.AppUser;

public interface AppUserRepository extends MongoRepository<AppUser, String>,AppUserRepositoryCustom  {

	AppUser findByEmail(String emailId);

	AppUser findByUserId(String id);
	
	AppUser findByContactNbr(String contactNbr);
	
//	@Query(value = "{}", fields = "{name : 1}")
//    List<AppUser> findNameAndId();

	@Query(value = "{'roles' : { $in : [ ?0] }}}", fields = "{ userId:1, firstName:1, middleName:1, lastName:1, contactNbr:1,email:1, serviceArea:1  }" )
	List<AppUser> findUsersByroleDetails(String role);

	@Query(value = "{}", fields = "{ userId:1, firstName:1, middleName:1, lastName:1, roles:1 }" )
	List<AppUser> getAllUserMinimum();
	
	List<AppUser> findByRolesInAndServiceAreaMapLocationWithin(String role, Sphere circle);
	
}
