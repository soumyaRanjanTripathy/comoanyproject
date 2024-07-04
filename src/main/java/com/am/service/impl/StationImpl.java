package com.am.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.entity.Station;
import com.am.repository.StationRepo;
import com.am.service.StationService;

@Service
public class StationImpl implements StationService{
	@Autowired
	private StationRepo srepo;
	@Override
	public List<Station> getAllStation() {
		
		return srepo.findAll();
	}
	@Override
	public String getStationNameById(int id) {
		return srepo.getStationNameById(id);
	}
	@Override
	public int getStationIdByName(String station_name) {
		
		return srepo.getStationIdByName(station_name);
	}
	@Override
	public int saveStation(Station station) {
		Station status=srepo.save(station);
		return status.getStation_id();
	}
	@Override
	public void deleteStationById(int station_id) {
	     srepo.deleteById(station_id);
	}
	@Override
	public Station getStationById(int station_id) {		
		return srepo.findById(station_id).get();
	}
	@Override
	public Station updateStation(Station station) {		
		return srepo.save(station);
	}
	
}
