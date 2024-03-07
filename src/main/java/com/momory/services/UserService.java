package com.momory.services;

import java.util.List;

import com.momory.entitys.Role;
import com.momory.entitys.Users;
import com.momory.payload.JwtRequest;
import com.momory.payload.JwtResponse;

public interface UserService {
	
	public String registerNewUser(Users users);
	
	public String updateUser(Users users);
	
	public String registerNewAdmin(Users users);
	
	public String createNewRole(Role role);
		
	public String getOtp(String phoneNoOrEmail);

	public  JwtResponse  verifyOtp(JwtRequest authenticationRequest);

	public List<Users> getAllUsers();

	public Users getUserById(String userId);

	public List<Role> getAllRoles();

	public Role getRoleById(String roleId);

	public List<Users> getAllAdmins();

	public String deleteByRoleId(String roleId);

	public String deleteByAdminId(String adminId);

	public String updateAdmin(Users admin);
}
