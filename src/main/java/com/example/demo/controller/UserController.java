 package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import javax.validation.valid;
import javax.persistence.EntityNotFoundException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domaine.Message;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.SigninRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

// origins = "http://localhost:4200"
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	private UserService userService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	private AuthenticationManager authenticationManger;
	
   @Autowired
 	PasswordEncoder encoder;
	
	@GetMapping("/pwd")
	public ResponseEntity<?> genPwd() throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		
		String encodedPassword = encoder.encode("123456789");
		
		return ResponseEntity.ok(new Message(encodedPassword));
	}
	
	@PostMapping("/register")
	
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest data)
	{
		if (userRepository.existeByEmail(data.getEmail())) {
			return ResponseEntity.badRequest()
.body(new Message("error: Email is already existe!"));	

		}
		
		//create new user 
		User user = new User(data.getEmail(),
				encoder.encode(data.getPassword()),
				true,
				data.getRole());
		
		userRepository.save(user);
		
		return ResponseEntity.ok(new Message ("user registred successfully"));
		
	
	}
	
 	
	
	
@RequestMapping(value= "/authenticate", method = RequestMethod.POST)
public ResponseEntity<?> loginUser(@Validated @RequestBody SigninRequest data ){
	
	Authentication authentication = authenticationManger.authenticate(
			new EmailPasswordAuthenticationToken(
					data.getEmail(),
					data.getPassword())
			);
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtTokenUtil.generateJwtToken(authentication);
	
	MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
	System.out.println(userDetails.getEmail());
	System.out.println(userDetails.getRole());
	return ResponseEntity.ok(new JwtResponse(jwt,
			                                    userDetails.getId(),
			                                    userDetails.getEmail(),
			                                    userDetails.getRole()
			                                   
			                                    
			                                    ));
}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		System.out.println("Get all users...")
		
		 return userService.getAll();
	}
	
	//get user by id rest
		@GetMapping("/users/{id}")
		public ResponseEntity<User> getUserById(@PathVariable Long id) {
			User user = userService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			return ResponseEntity.ok().body(user);
					
			
		}
	
	//create employee rest api
		@PostMapping("/users")
		public User CreateUser(@RequestBody User user) {
			return userService.save(user);
			
			
		}
	
	
	//update user res api
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		
		System.out.println("Update User with ID "+ id + "....");
		
		Optional<User> userInfo = userService.findById(id);
		
		if( userInfo.isPresent()) {
			User utilisateur = userInfo.get();
			utilisateur.setFirstName(utilisateur.getFirstName());
			utilisateur.setLastName(utilisateur.getLastName());
			utilisateur.setEmail(utilisateur.getEmail());
			utilisateur.setPassword(utilisateur.getPassword());
			return new ResponseEntity<>(userService.save(utilisateur), HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}}
		
	//delete 
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));
		
		userService.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	 
}
