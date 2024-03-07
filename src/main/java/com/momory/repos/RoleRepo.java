package com.momory.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.momory.entitys.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {
	
	@Query(value = "SELECT max(id) FROM Role")
	String max();

	public Role findByRoleName(String roleName);

}
