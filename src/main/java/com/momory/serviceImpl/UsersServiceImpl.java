//package com.momory.serviceImpl;
//
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.transaction.Transactional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.momory.config.AppConstants;
//import com.momory.entitys.Address;
//import com.momory.entitys.Cart;
//import com.momory.entitys.CartItem;
//import com.momory.entitys.Role;
//import com.momory.entitys.Users;
//import com.momory.exception.APIException;
//import com.momory.exception.ResourceNotFoundException;
//import com.momory.payload.AddressDTO;
//import com.momory.payload.CartDTO;
//import com.momory.payload.ProductDTO;
//import com.momory.payload.UserDTO;
//import com.momory.payload.UserResponse;
//import com.momory.repos.AddressRepo;
//import com.momory.repos.RoleRepo;
//import com.momory.repos.UserRepo;
//import com.momory.services.CartService;
//import com.momory.services.UserService;
//
//@Transactional
//@Service
//public class UsersServiceImpl implements UserService {
//
//	@Autowired
//	private UserRepo userRepo;
//
//	@Autowired
//	private RoleRepo roleRepo;
//
//	@Autowired
//	private AddressRepo addressRepo;
//
//	@Autowired
//	private CartService cartService;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	@Override
//	public UserDTO registerUser(UserDTO userDTO) {
////
////		try {
////			Users user = modelMapper.map(userDTO, User.class);
////
////			Cart cart = new Cart();
////			user.setCart(cart);
////
////			Role role = roleRepo.findById(AppConstants.USER_ID).get();
////			//user.getRoles().add(role);
////
////			String country = userDTO.getAddress().getCountry();
////			String state = userDTO.getAddress().getState();
////			String city = userDTO.getAddress().getCity();
////			String pincode = userDTO.getAddress().getPincode();
////			String street = userDTO.getAddress().getStreet();
////			String buildingName = userDTO.getAddress().getBuildingName();
////
////			Address address = addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(country, state,
////					city, pincode, street, buildingName);
////
////			if (address == null) {
////				address = new Address(country, state, city, pincode, street, buildingName);
////
////				address = addressRepo.save(address);
////			}
////
////			user.setAddresses(List.of(address));
////
////			Users registeredUser = userRepo.save(user);
////
////			cart.setUser(registeredUser);
////
////			userDTO = modelMapper.map(registeredUser, UserDTO.class);
////
////			userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));
////
////			return userDTO;
////		} catch (DataIntegrityViolationException e) {
////			throw new APIException("User already exists with emailId: " + userDTO.getEmail());
////		}
//
//	}
//
//	@Override
//	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
//		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
//				: Sort.by(sortBy).descending();
//
//		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
//		
//		Page<Users> pageUsers = userRepo.findAll(pageDetails);
//		
//		List<Users> users = pageUsers.getContent();
//
//		if (users.size() == 0) {
//			throw new APIException("No User exists !!!");
//		}
//
//		List<UserDTO> userDTOs = users.stream().map(user -> {
//			UserDTO dto = modelMapper.map(user, UserDTO.class);
//
//			if (user.getAddresses().size() != 0) {
//				dto.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));
//			}
//
//			CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);
//
//			List<ProductDTO> products = user.getCart().getCartItems().stream()
//					.map(item -> modelMapper.map(item.getProduct(), ProductDTO.class)).collect(Collectors.toList());
//
//			dto.setCart(cart);
//
//			dto.getCart().setProducts(products);
//
//			return dto;
//
//		}).collect(Collectors.toList());
//
//		UserResponse userResponse = new UserResponse();
//		
//		userResponse.setContent(userDTOs);
//		userResponse.setPageNumber(pageUsers.getNumber());
//		userResponse.setPageSize(pageUsers.getSize());
//		userResponse.setTotalElements(pageUsers.getTotalElements());
//		userResponse.setTotalPages(pageUsers.getTotalPages());
//		userResponse.setLastPage(pageUsers.isLast());
//		
//		return userResponse;
//	}
//
//	@Override
//	public UserDTO getUserById(Long userId) {
//		Users user = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
//
//		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//
//		userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));
//
//		CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);
//
//		List<ProductDTO> products = user.getCart().getCartItems().stream()
//				.map(item -> modelMapper.map(item.getProduct(), ProductDTO.class)).collect(Collectors.toList());
//
//		userDTO.setCart(cart);
//
//		userDTO.getCart().setProducts(products);
//
//		return userDTO;
//	}
//
//	@Override
//	public UserDTO updateUser(Long userId, UserDTO userDTO) {
//		Users user = userRepo.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
//
//		String encodedPass = passwordEncoder.encode(userDTO.getPassword());
//
//		user.setFirstName(userDTO.getFirstName());
//		user.setLastName(userDTO.getLastName());
//		user.setMobileNumber(userDTO.getMobileNumber());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(encodedPass);
//
//		if (userDTO.getAddress() != null) {
//			String country = userDTO.getAddress().getCountry();
//			String state = userDTO.getAddress().getState();
//			String city = userDTO.getAddress().getCity();
//			String pincode = userDTO.getAddress().getPincode();
//			String street = userDTO.getAddress().getStreet();
//			String buildingName = userDTO.getAddress().getBuildingName();
//
//			Address address = addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(country, state,
//					city, pincode, street, buildingName);
//
//			if (address == null) {
//				address = new Address(country, state, city, pincode, street, buildingName);
//
//				address = addressRepo.save(address);
//
//				user.setAddresses(List.of(address));
//			}
//		}
//
//		userDTO = modelMapper.map(user, UserDTO.class);
//
//		userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));
//
//		CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);
//
//		List<ProductDTO> products = user.getCart().getCartItems().stream()
//				.map(item -> modelMapper.map(item.getProduct(), ProductDTO.class)).collect(Collectors.toList());
//
//		userDTO.setCart(cart);
//
//		userDTO.getCart().setProducts(products);
//
//		return userDTO;
//	}
//
//	@Override
//	public String deleteUser(Long userId) {
//		Users user = userRepo.findById(userId)  
//				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
//
//		List<CartItem> cartItems = user.getCart().getCartItems();
//		Long cartId = user.getCart().getCartId();
//
//		cartItems.forEach(item -> {
//
//			Long productId = item.getProduct().getProductId();
//
//			cartService.deleteProductFromCart(cartId, productId);
//		});
//
//		userRepo.delete(user);
//
//		return "User with userId " + userId + " deleted successfully!!!";
//	}
//
//}