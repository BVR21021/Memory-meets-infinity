package com.momory.payload;



import com.momory.entitys.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private Users user;
	private String jwtToken;
	private String status;
	private String message;

}