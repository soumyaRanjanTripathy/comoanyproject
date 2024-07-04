package com.am.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.entity.Nhistorydata;
import com.am.repository.NhistorydataRepo;
import com.am.service.NhistoryService;
@Service
public class NhistorydataImpl implements NhistoryService{
	@Autowired
	private NhistorydataRepo nhistorydataRepo;
	

	@Override
	public List<Nhistorydata> getAllNhistorydata() {
		
		return null;
	}

	@Override
	public int addHistory(String user_name, String station_name, String parameter_name, Double parameter_unit, String status) {
		return nhistorydataRepo.addHistory(user_name, station_name, parameter_name, parameter_unit, status);		
	}
	

	
	  @Override public List<Nhistorydata> getforGdata() {	  
		  return nhistorydataRepo.getforGdata(); 
	  }
	 

}
