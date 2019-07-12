package com.gsc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.java4s.model.location;

public class UserLocationRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		location location = new location();
//		customer.setCustId(rs.getInt("CUST_ID"));
//		customer.setName(rs.getString("NAME"));
//		customer.setAge(rs.getInt("AGE"));
		
		location.setUserId(String.valueOf(rs.getInt("userid")));
		location.setLongitude(rs.getString("Longitude").toString());
		location.setLattitude(rs.getString("Latitude").toString());
		location.setCreatedTime(rs.getString("creation_time").toString());
		
		
		return location;
	}

}
