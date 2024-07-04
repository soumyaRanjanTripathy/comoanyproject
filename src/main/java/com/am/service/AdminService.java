package com.am.service;

import java.util.List;

import com.am.entity.Admin;
import com.am.entity.User;


public interface AdminService {
	public Admin createAdmin(Admin admin);	
	public List<Admin> getAllAdmin();
	public void deleteAdminById(int admin_id);
	public Admin getAdminById(int admin_id);
	public Admin updateAdmin(Admin admin);
	public int checkAdminExist(String admin_email,String admin_password);
}
