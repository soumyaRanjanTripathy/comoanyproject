package com.am.service;


import java.util.List;



import com.am.entity.Nhistorydata;

public interface NhistoryService {

	List<Nhistorydata> getAllNhistorydata();
	public int addHistory(String user_name,String station_name,String parameter_name,Double parameter_unit,String status);
	public List<Nhistorydata> getforGdata(); 
}
