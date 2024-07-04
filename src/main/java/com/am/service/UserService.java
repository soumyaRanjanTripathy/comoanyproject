package com.am.service;

import java.util.List;

import com.am.entity.Station;
import com.am.entity.User;

public interface UserService {

	public User createUser(User user);	
	public boolean checkEmail(String email);
	public int checkUserExist(String email,String password);
	public List<User> getAllUser();
	public void deleteUserById(int id);
	public User getUserById(int id);
	public User updateUser(User user);
}
