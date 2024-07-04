package com.am.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.entity.Station;
import com.am.entity.User;
import com.am.repository.UserRepo;
import com.am.service.UserService;
@Service
public class UserImpl implements UserService{
	@Autowired
	private UserRepo userRepo;

	@Override
	public User createUser(User user) {		
		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public int checkUserExist(String email, String password) {
		return userRepo.checkUserExist(email, password);
	}

	@Override
	public List<User> getAllUser() {		
		return userRepo.findAll();
	}

	@Override
	public void deleteUserById(int id) {
		userRepo.deleteById(id);
	}

	@Override
	public User getUserById(int id) {
		return userRepo.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}



}
