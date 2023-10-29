package com.app.project.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.project.entity.User;
import com.app.project.entity.Role;
import com.app.project.repository.UserRepository;

@Component
@Order(1)
public class DatabaseLoader implements CommandLineRunner {
	
	@Autowired
	private UserRepository repo;

	@Override
	public void run(String... args) throws Exception {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		User user1 = new User("administrator","12345678","Super","Admin",
				"admin@mail.com","Berlin, Germany","System Administrator","Version 2.03.04",Role.ADMIN);
		user1.setPassword(passwordEncoder.encode(user1.getPassword()));

		User user2 = new User("zaphodfourth","12345678","Zaphod","Beeblebrox",
				"zaphod@gmail.com","Somewhere near the Betelgeuse","President of the Galaxy","Lying on Beach",Role.USER);
		user2.setPassword(passwordEncoder.encode(user2.getPassword()));
		
		User user3 = new User("captainpicard","12345678","Jean-Luc","Picard",
				"picard@gmail.com","La Barre, France","Captain of USS Enterprise","Everything Space",Role.USER);
		user3.setPassword(passwordEncoder.encode(user3.getPassword()));

		repo.saveAll(Arrays.asList(user1, user2, user3));
	}
}
