package com.am.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.am.entity.User;



public interface UserRepo extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String email);
	
	@Query(value="select count(*)  from ncompany.user  WHERE email = ?1 and password = ?2" ,nativeQuery=true)
    public int checkUserExist(String email, String password);
	
}
