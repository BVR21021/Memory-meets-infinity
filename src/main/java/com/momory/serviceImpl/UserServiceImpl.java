package com.momory.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momory.entitys.Role;
import com.momory.entitys.Users;
import com.momory.payload.JwtRequest;
import com.momory.payload.JwtResponse;
import com.momory.repos.RoleRepo;
import com.momory.repos.UserRepo;
import com.momory.services.JwtService;
import com.momory.services.UserService;
import com.momory.util.CustomizedUserId;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private OtpService otpService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public String registerNewUser(Users users) {

		Set<Role> set = new HashSet<>();

		try {
			Optional<Users> user = userRepo.findByEmail(users.getEmail());

			Optional<Users> user1 = userRepo.findByMobileNumber(users.getMobileNumber());

			if (user.isPresent()) {
				return "User Already Registered With This Email";
			}

			if (user1.isPresent()) {
				return "User Already Registered With This Mobile Number";
			}

			Role role = new Role();

			if (users.getEmail().equalsIgnoreCase("bvrit77@gmail.com")) {
				role = roleRepo.findByRoleName(" SUPER-ADMIN");

			} else {
				role = roleRepo.findByRoleName("USER");
			}

			set.add(role);
			users.setRole(set);
			users.setCreated_date(new Timestamp(System.currentTimeMillis()));

			users.setId(CustomizedUserId.customized_user_id(userRepo.max()));

			Users u = userRepo.save(users);
			return u.getFullname() + " Registerd Successfully";

		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing Went Wrong";
		}
	}

	@Override
	public String updateUser(Users users) {

		try {

			Optional<Users> user = userRepo.findByEmail(users.getEmail());

			user.get().setEmail(users.getEmail());
			user.get().setFullname(users.getFullname());
			user.get().setMobileNumber(users.getMobileNumber());
			user.get().setUpdated_date(new Timestamp(System.currentTimeMillis()));
			userRepo.save(user.get());
			return user.get().getFullname() + " Updated Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing Went Wrong";

		}
	}

	@Override
	public String registerNewAdmin(Users users) {

		Set<Role> set = new HashSet<>();

		try {

			Optional<Users> user = userRepo.findByEmail(users.getEmail());

			Optional<Users> user1 = userRepo.findByMobileNumber(users.getMobileNumber());

			if (user.isPresent()) {
				return "Admin Already Registered With This Email";
			}

			if (user1.isPresent()) {
				return "Admin Already Registered With This Mobile Number";
			}

			String role = "";

			for (Role r : users.getRole()) {
				role = r.getRoleName();

			}

			Role role1 = roleRepo.findByRoleName(role);
			set.add(role1);
			users.setRole(set);
			users.setCreated_date(new Timestamp(System.currentTimeMillis()));

			users.setId(CustomizedUserId.customized_user_id(userRepo.max()));

			Users u = userRepo.save(users);
			return u.getFullname() + " Registerd Successfully";

		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing Went Wrong";

		}
	}

	@Override
	public String updateAdmin(Users admin) {

		try {

			Optional<Users> user = userRepo.findByEmail(admin.getEmail());

			user.get().setEmail(admin.getEmail());
			user.get().setFullname(admin.getFullname());
			user.get().setMobileNumber(admin.getMobileNumber());
			
			user.get().setUpdated_date(new Timestamp(System.currentTimeMillis()));
			userRepo.save(user.get());
			return user.get().getFullname() + " Updated Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing Went Wrong";

		}

	}

	@Override
	public String createNewRole(Role role) {
		String msg = "";
		try {

			role.setId(CustomizedUserId.customizedRollId(roleRepo.max()));
			roleRepo.save(role);
			msg = "Role Created Successfully";

		} catch (Exception e) {
			msg = "Something Went Wrong";
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String getOtp(String phoneNoOrEmail) {
		String returnOtp = "";

		try {
			returnOtp = otpService.generateOtp(phoneNoOrEmail);

		} catch (Exception e) {

		}

		return returnOtp;
	}

	@Override
	public JwtResponse verifyOtp(JwtRequest authenticationRequest) {

		JwtResponse jwtResponse = new JwtResponse();

		try {

			System.out.println(authenticationRequest.getEmailOrMobileNumber());

			System.out.println(otpService.getCacheOtp(authenticationRequest.getEmailOrMobileNumber()) + "    otp");

			if (authenticationRequest.getOtp()
					.equals(otpService.getCacheOtp(authenticationRequest.getEmailOrMobileNumber()))) {

				jwtResponse = jwtService.createJwtToken(authenticationRequest);

				otpService.clearOtp(authenticationRequest.getEmailOrMobileNumber());

			} else {
				jwtResponse.setJwtToken(null);
				jwtResponse.setUser(null);
				jwtResponse.setStatus("failure");
				jwtResponse.setMessage("Otp is either expired or incorrect");

			}

		} catch (Exception e) {
			e.printStackTrace();
			jwtResponse.setJwtToken(null);
			jwtResponse.setUser(null);
			jwtResponse.setStatus("failure");
			jwtResponse.setMessage("Otp is either expired or incorrect");
		}

		return jwtResponse;
	}

	@Override
	public List<Users> getAllUsers() {

		List<Users> users = new ArrayList<>();

		try {
			users = userRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Users getUserById(String userId) {

		Users users = new Users();

		try {
			users = userRepo.findById(userId).get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> role = new ArrayList<>();
		try {
			role = roleRepo.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public Role getRoleById(String roleId) {

		Role role = new Role();

		try {
			role = roleRepo.findById(roleId).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<Users> getAllAdmins() {

		List<Users> users = new ArrayList<>();

		try {
			users = userRepo.findAllAdmins();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public String deleteByRoleId(String roleId) {

		try {
			roleRepo.deleteById(roleId);
			return "Role Deleted SuccessFully";

		} catch (Exception e) {
			e.printStackTrace();
			return "Somethins Went Wrong!";
		}
	}

	@Override
	public String deleteByAdminId(String adminId) {

		try {
			userRepo.deleteById(adminId);
			return "Admin Deleted SuccessFully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Somethins Went Wrong!";
		}
	}

}
