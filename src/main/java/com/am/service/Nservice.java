package com.am.service;

import java.util.List;
import com.am.entity.Ndata;

public interface Nservice {

	public List<Ndata> getAllNdata();
	public Ndata addnic(Ndata Ndata);
	public Ndata getNdataById(int id);
	public Ndata updateNdata(Ndata ndata);
	public void deleteNNdataById(int id);
	public List<String> getNicUser();
	public List<Integer> getStationId(String uname);
	public int checkNicExist(String user_name, int station_id);
	public Ndata findSpecificNic(String date,String todate ,String user_name,int station_id);
	
}
