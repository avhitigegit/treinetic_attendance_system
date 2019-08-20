package com.attend.demo.repository;

import com.attend.demo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    @Query("{ 'id' : ?0 }")
    Role findRoleByID(String id);
}
