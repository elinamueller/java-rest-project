package com.app.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.project.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}
