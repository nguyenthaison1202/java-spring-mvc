package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication
@SpringBootApplication(
		//disable security
		exclude = SecurityAutoConfiguration.class
)
public class DemoApplication {
//	@Autowired
//	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner insertUser(){
//		return args -> {
//			Role user = new Role();
//			user.setName("USER");
//			user.setDescription("user description");
//			roleRepository.save(user);
//			Role user2 = new Role();
//			user2.setName("ADMIN");
//			user2.setDescription("ADMIN description");
//			roleRepository.save(user2);
//		};
//	}

}
