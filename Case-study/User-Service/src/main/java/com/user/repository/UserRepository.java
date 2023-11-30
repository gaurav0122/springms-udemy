package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.emailId=?1")
	User findByEmailId(String email);

}
