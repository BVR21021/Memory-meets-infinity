//package com.momory.serviceImpl;
//
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.app.config.UserInfoConfig;
//import com.momory.entitys.Users;
//import com.momory.exception.ResourceNotFoundException;
//import com.momory.repos.UserRepo;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private UserRepo userRepo;
//
////	@Override
////	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		Optional<Users> user = userRepo.findByEmail(username);
////		
////		return user.map(UserInfoConfig::new).orElseThrow(() -> new ResourceNotFoundException("User", "email", username));
////	}
//}