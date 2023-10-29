package com.app.project.service;

import java.util.List;

import com.app.project.entity.User;

public interface UserService {
	
	User getProfileByUsername(String username);
	
	User saveProfile(User user);
	
	List<User> getAllProfiles();
	
	void deleteProfileById(Long id);
	
	User updateProfile(String username, User user);

}
