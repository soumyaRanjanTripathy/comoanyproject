package com.am.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="station")
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int station_id;
	private String station_name;
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	@Override
	public String toString() {
		return "Station [station_id=" + station_id + ", station_name=" + station_name + "]";
	}
	
}
