package com.momory.entitys;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
	
	@Id
	private String id;
	
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	
	@Size(min = 5, max = 20, message = "First Name must be between 5 and 30 characters long")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must not contain numbers or special characters")
	private String fullname;
	
	@Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
	@Pattern(regexp = "^\\d{10}$", message = "Mobile Number must contain only Numbers")
	private String mobileNumber;
	
	private Timestamp created_date;
	private Timestamp updated_date;
	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinTable(name = "USER_ROLE",
	            joinColumns = {
	                    @JoinColumn(name = "USER_ID")
	            },
	            inverseJoinColumns = {
	                    @JoinColumn(name = "ROLE_ID")
	            }
	    )
	    private Set<Role> role;
	 
	    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		@JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
		private List<Address> addresses = new ArrayList<>();

		@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
		private Cart cart;
}
