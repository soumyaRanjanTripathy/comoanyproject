package com.am.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.am.entity.Admin;

public interface AdminRepo  extends JpaRepository<Admin,Integer> {

	@Query(value="select count(*)  from ncompany.admin  WHERE admin_email = ?1 and admin_password = ?2" ,nativeQuery=true)
    public int checkUserExist(String admin_email, String admin_password);
}
