package com.am.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.am.entity.Station;

public interface StationRepo extends JpaRepository<Station,Integer>{
	@Query(value="Select Station_name from ncompany.station where station_id=?1",nativeQuery=true)
	public String getStationNameById(int id);
	@Query(value="Select Station_id from ncompany.station where station_name=?1",nativeQuery=true)
	public int getStationIdByName(String station_name);
}
