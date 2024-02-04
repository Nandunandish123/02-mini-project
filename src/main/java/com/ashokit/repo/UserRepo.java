package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail (String email);
	
	
	
	public User findByEmailAndPwd(String email, String string);
}
