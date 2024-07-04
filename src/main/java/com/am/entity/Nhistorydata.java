package com.am.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="historydata_system")
public class Nhistorydata {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sl_no;
	private String user_name;
	private  String station_name;
	private String parameter_name;
	private Double parameter_unit;
	private String  status;
	private String lastModifiedAt;
	
	public int getSl_no() {
		return sl_no;
	}



	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}



	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getStation_name() {
		return station_name;
	}



	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}



	public String getParameter_name() {
		return parameter_name;
	}



	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}



	public Double getParameter_unit() {
		return parameter_unit;
	}



	public void setParameter_unit(Double parameter_unit) {
		this.parameter_unit = parameter_unit;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getLastModifiedAt() {
		return lastModifiedAt;
	}



	public void setLastModifiedAt(String lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}



	@Override
	public String toString() {
		return "Ndata [sl_no=" + sl_no + ", user_name=" + user_name + ", station_name=" + station_name
				+ ", parameter_name=" + parameter_name + ", parameter_unit=" + parameter_unit + ", status=" + status
				+ ", lastModifiedAt=" + lastModifiedAt + "]";
	}








}
