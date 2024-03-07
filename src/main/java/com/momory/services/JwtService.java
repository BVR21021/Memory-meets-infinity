package com.momory.services;

import com.momory.payload.JwtRequest;
import com.momory.payload.JwtResponse;

public interface JwtService {
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;
	

}
