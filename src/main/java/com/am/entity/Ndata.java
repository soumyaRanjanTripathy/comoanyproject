package com.am.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
@Data
@Entity
@Table(name="N_system")
public class Ndata {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sl_no")
	private int sl_no;
	@Column(name="user_name")
	private String user_name;
	@Column(name="parameter_name")
	private String parameter_name;
	@Column(name="parameter_unit")
	private Double parameter_unit;
	@Column(name="status")
	private String  status;
	 @Column(name = "date")	
	 private String date;
	
	
	@JoinColumn(name = "station_id")
	@OneToOne
	private Station  station;
	
	@Transient
	private String station_name;
	@Transient
	private String todate;

	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "Ndata [sl_no=" + sl_no + ", user_name=" + user_name + ", parameter_name=" + parameter_name
				+ ", parameter_unit=" + parameter_unit + ", status=" + status + ", date=" + date + ", station="
				+ station + ", station_name=" + station_name + ", todate=" + todate + "]";
	}
	
	
	
	
}
