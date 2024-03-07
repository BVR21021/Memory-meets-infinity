package com.momory.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.momory.entitys.Users;
import com.momory.payload.JwtRequest;
import com.momory.payload.JwtResponse;
import com.momory.repos.UserRepo;
import com.momory.services.JwtService;
import com.momory.util.JwtUtil;

@Service
public class JwtServiceIMPL implements UserDetailsService, JwtService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo loginRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getEmailOrMobileNumber();
		authenticate(userName);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		Optional<Users> user = loginRepo.findEmailOrMobileNumber(userName);
		
		JwtResponse jwtResponse=new JwtResponse();
		jwtResponse.setUser(user.get());
		jwtResponse.setJwtToken(newGeneratedToken);
		jwtResponse.setStatus("Success");
		jwtResponse.setMessage("Otp verified successfully");

		return jwtResponse;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("loded");
		Optional<Users> user = loginRepo.findEmailOrMobileNumber(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.get().getEmail(), "", getAuthority(user.get()));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public Set getAuthority(Users user) {
		//System.out.println("granted");
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	public void authenticate(String userName) throws Exception {
		try {

	        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
