package com.am.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.entity.Admin;
import com.am.entity.User;
import com.am.repository.AdminRepo;
import com.am.service.AdminService;
@Service
public class AdminImpl implements AdminService {
	@Autowired
	public AdminRepo adminRepo;

	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

	@Override
	public List<Admin> getAllAdmin() { 
		return adminRepo.findAll();
	}

	@Override
	public void deleteAdminById(int admin_id) {
		adminRepo.deleteById(admin_id);		
	}

	@Override
	public Admin getAdminById(int admin_id) { 
		return adminRepo.findById(admin_id).get();
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		 return adminRepo.save(admin);
	}

	@Override
	public int checkAdminExist(String admin_email, String admin_password) {
		 return adminRepo.checkUserExist(admin_email, admin_password);
	}
}
