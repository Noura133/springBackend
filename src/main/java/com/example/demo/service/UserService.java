package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

	
	
	/*@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAll(){
		System.out.println("Get all users..");
		return userRepository.findAll(Sort.by("firstName").ascending());
	}
	
	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	public long save(User User) {
		System.out.println("save all users...");
		User user = new User();
		user.setFirstName(User.getFirstName());
		user.setLastName(User.getLastName());
		user.setTelephone(User.getTelephone());
		user.setEmail(User.getEmail());
		user.setPassword(User.getPassword());
		user.setActive(User.isActive());
		user.setRole(User.getRole());
		
		return userRepository.save(user).getId();		
		
	}
	
	public void update(long id, User User) {
		Optional<User> userr = userRepository.findById(id);
		if(userr.isPresent()) {
			User user = userr.get();
			user.setFirstName(User.getFirstName());
			user.setLastName(User.getLastName());
			user.setTelephone(User.getTelephone());
			user.setEmail(User.getEmail());
			user.setPassword(User.getPassword());
			user.setActive(User.isActive());
			user.setRole(User.getRole());
			userRepository.save(user);
		}
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public void delete(long id) {
		Optional<User> user = userRepository.findById(id);
		user.ifPresent(userRepository::delete);
	}
	*/
}
