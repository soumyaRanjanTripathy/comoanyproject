package com.am.service;

import java.util.List;
import java.util.Optional;

import com.am.entity.Station;

public interface StationService {
	
	public List<Station> getAllStation();
	public String getStationNameById(int id);
	public int getStationIdByName(String station_name);
	public int saveStation(Station station);
	public void deleteStationById(int station_id);
	public Station getStationById(int station_id);
	public Station updateStation(Station station);
}
