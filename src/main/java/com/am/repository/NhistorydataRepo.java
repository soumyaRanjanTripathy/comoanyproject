package com.am.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.am.entity.Nhistorydata;

public interface NhistorydataRepo  extends JpaRepository<Nhistorydata,Integer> {
	@Modifying
	@Transactional
	@Query(value="insert into ncompany.historydata_system (user_name,station_name,parameter_name,parameter_unit,status,last_modified_at)values(:user_name,:station_name,:parameter_name,:parameter_unit,:status,now())",nativeQuery=true )
	public int addHistory(@Param("user_name")String user_name,@Param("station_name")String station_name,@Param("parameter_name")String parameter_name,@Param("parameter_unit")Double parameter_unit,@Param("status")String status);
	
	
	  @Query(value="select * from historydata_system where last_modified_at>now() - interval 24 hour" ,nativeQuery=true) 
	  public List<Nhistorydata> getforGdata();
	 

}
