package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
class SpringbootBackendApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}
	/*void testUsername()
	{
		User user = userRepository.findByEmailId("noura@gmail.com");
		
		System.out.println(user);
	}*/

}
