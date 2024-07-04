package com.am.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.am.entity.Ndata;

public interface Nrepo extends JpaRepository<Ndata,Integer>{
	@Query(value="Select distinct user_name from ncompany.n_system",nativeQuery=true)
	public List<String> getNicUser();
	
	@Query(value="Select Station_id from ncompany.n_system where n_system.user_name=?1",nativeQuery=true)
	public List<Integer> getStationId(String uname);
	
	
	@Query(value="select count(*)  from ncompany.n_system  WHERE user_name = ?1 and station_id = ?2" ,nativeQuery=true)
    public int checkNicExist(String user_name, int station_id);
	
	@Query(value="SELECT * FROM ncompany.n_system where date between ?1 and ?2 and user_name=?3 and station_id=?4" ,nativeQuery=true)
    public Ndata findSpecificNic(String date,String todate ,String user_name,int station_id);

	
	

	
}
