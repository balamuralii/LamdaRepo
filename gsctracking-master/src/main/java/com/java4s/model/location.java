package com.java4s.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "location")
public class location {
	
	private String userId;
	private String lattitude;
	private String longitude;
	private String createdTime;
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String string) {
		this.lattitude = string;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setLongitudee(String string) {
		// TODO Auto-generated method stub
		
	}
	

}
