package com.momory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.momory.entitys.Role;
import com.momory.entitys.Users;
import com.momory.payload.JwtRequest;
import com.momory.payload.JwtResponse;
import com.momory.services.UserService;


@RestController
public class MemoryMeetsInfinityController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "registerNewUser")
	public ResponseEntity<String> registerNewUser(@RequestBody Users users) {
		return new ResponseEntity<String>(userService.registerNewUser(users), HttpStatus.OK);
	}
	
	@PostMapping(value = "createNewRole")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<String> createNewRole(@RequestBody Role role) {
		return new ResponseEntity<String>(userService.createNewRole(role), HttpStatus.OK);
	}
	
	@PostMapping(value = "registerNewAdmin")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<String> registerNewAdmin(@RequestBody Users users) {
		return new ResponseEntity<String>(userService.registerNewAdmin(users), HttpStatus.OK);
	}
	
	@GetMapping(value = "requestOtp")
	public ResponseEntity<String> getOtp(@RequestParam String phoneNoOrEmail) {
		return new ResponseEntity<String>(userService.getOtp(phoneNoOrEmail), HttpStatus.OK);
	}

	@PostMapping(value = "verifyOtp")
	public ResponseEntity<JwtResponse> verifyOtp(@RequestBody JwtRequest authenticationRequest) {
		return new ResponseEntity<JwtResponse>(userService.verifyOtp(authenticationRequest), HttpStatus.OK);
	}

	@GetMapping(value = "getAllUsers")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "getUserById")
	public ResponseEntity<Users> getUserById(@RequestParam String userId) {
		return new ResponseEntity<Users>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllAdmins")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<List<Users>> getAllAdmins() {
		return new ResponseEntity<List<Users>>(userService.getAllAdmins(), HttpStatus.OK);
	}
	
	@GetMapping(value = "getAllRoles")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<List<Role>> getAllRoles(){
		return new ResponseEntity<List<Role>>(userService.getAllRoles(),HttpStatus.OK);
	}
	
	@GetMapping(value = "getRoleById")
	public ResponseEntity<Role> getRoleById(@RequestParam String roleId){
		return new ResponseEntity<Role>(userService.getRoleById(roleId),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteByRoleId")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<String> deleteByRoleId(@RequestParam String roleId){
		return new ResponseEntity<String>(userService.deleteByRoleId(roleId),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteByAdminId")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<String> deleteByAdminId(@RequestParam String adminId){
		return new ResponseEntity<String>(userService.deleteByAdminId(adminId),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "updateAdmin")
	@PreAuthorize("hasRole(' SUPER-ADMIN')")
	public ResponseEntity<String> updateAdmin(@RequestBody Users admin){
		return new ResponseEntity<String>(userService.updateAdmin(admin),HttpStatus.OK);
	}

}