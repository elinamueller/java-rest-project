package com.app.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.project.entity.Role;
import com.app.project.entity.User;
import com.app.project.exception.ResourceNotFoundException;
import com.app.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	private static List<User> userList = new ArrayList<>();
	
	@Cacheable(value = "userCache", key = "#username")
	public User getProfileByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User saveProfile(User user) {
		return userRepo.save(user);
	}
	
	//admin
	public List<User> getAllProfiles() {
		List<User> userList = (List<User>) userRepo.findAll();
		return userList;
	}	
	
	//admin
	public void deleteProfileById(Long id) {	
		if(!userRepo.existsById(id)) {
			throw new ResourceNotFoundException(
				"User with id " + id + " does not exist.");
		}
		userRepo.deleteById(id);
	}
	
    @CachePut(value = "userCache", key = "#username")
    @Transactional 
	public User updateProfile(String username, User user) {
		
		User existUser = getProfileByUsername(username);
		existUser.setUsername(user.getUsername());
		existUser.setPassword(user.getPassword());
		existUser.setEmail(user.getEmail());
		existUser.setFirstname(user.getFirstname());
		existUser.setLastname(user.getLastname());
		existUser.setLocation(user.getLocation());
		existUser.setOccupation(user.getOccupation());
		existUser.setInterests(user.getInterests());
		return userRepo.save(existUser);
		
	}

}
