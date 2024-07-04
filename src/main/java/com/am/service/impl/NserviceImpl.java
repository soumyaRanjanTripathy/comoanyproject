package com.am.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.am.entity.Ndata;
import com.am.repository.Nrepo;
import com.am.service.Nservice;
@Service
public class NserviceImpl implements Nservice{
	@Autowired
	private Nrepo nrepo;
	@Override
	public List<Ndata> getAllNdata() {		
		return nrepo.findAll();
	}

	@Override
	public Ndata addnic(Ndata ndata) {		
		return nrepo.save(ndata);
	}

	@Override
	public Ndata getNdataById(int id) {		
		return nrepo.findById(id).get();
	}

	@Override
	public Ndata updateNdata(Ndata ndata) {		
		return nrepo.save(ndata);
	}

	@Override
	public void deleteNNdataById(int id) {
		nrepo.deleteById(id);
		
	}

	@Override
	public List<String> getNicUser() {		
		return nrepo.getNicUser();
	}

	@Override
	public List<Integer> getStationId(String uname) {
		
		return nrepo.getStationId(uname);
	}

	@Override
	public int checkNicExist(String user_name, int station_id) {
		
		return nrepo.checkNicExist(user_name, station_id);
	}

	@Override
	public Ndata findSpecificNic(String date, String todate, String user_name, int station_id) {
		
		return nrepo.findSpecificNic(date, todate, user_name, station_id);
	}

	
	

	
}
