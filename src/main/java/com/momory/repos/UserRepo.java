package com.momory.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.momory.entitys.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {
	
	
	@Query(value = "SELECT max(id) FROM Users")
	String max();
	
	public Optional<Users> findByEmail(String email);
	
	public Optional<Users> findByMobileNumber(String mobilNumber);
	
	@Query(value = "SELECT * FROM Users where email=?1 OR mobile_number=?1", nativeQuery = true)
	public Optional<Users> findEmailOrMobileNumber(String emailOrMobileNumber);

	
	@Query("SELECT  u FROM Users u JOIN u.role r WHERE r.id IN ('ROL240000003', 'ROL240000002')")
    List<Users> findAllAdmins();
	
	@Query("SELECT u FROM User u JOIN FETCH u.addresses a WHERE a.addressId = ?1")
	List<Users> findByAddress(Long addressId);

}
