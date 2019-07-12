package com.java4s.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gsc.util.UserLocationRowMapper;
import com.java4s.model.location;

@Repository
public class SpringJava4sDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String GET_ALL_LOCATIONS_SQL = "select * from location_master";
	private static final String INSERT_LOCATION = "INSERT INTO location_master(userid,Longitude,Latitude,creation_time) values(:userid,:Longitude,:Latitude,CURTIME())";
	private static final String GET_LOCATION_ONUSERID = "SELECT * FROM location_master WHERE userid = ? order by creation_time desc LIMIT 1";
	public List<location> isData() {
		
		List<location> customers = new ArrayList<location>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_LOCATIONS_SQL);
		
		for (Map<String, Object> row : rows) {
			location location = new location();
			
			location.setUserId(row.get("userid").toString());
			location.setLongitude(row.get("Longitude").toString());
			location.setLattitude(row.get("Latitude").toString());
			location.setCreatedTime(row.get("creation_time").toString());
			customers.add(location);
		}

		return customers;
		
	}


	public int saveLocation(location location) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().
				addValue("userid", location.getUserId()).addValue("Latitude", location.getLattitude()).addValue("Longitude",location.getLongitude());
		return namedParameterJdbcTemplate.update(INSERT_LOCATION,namedParameters);
		
	}
	
	
	
	public location findByCustomerId(String custId){
		 
		
	 int cust = Integer.parseInt(custId);
		@SuppressWarnings("unchecked")
		location location = (com.java4s.model.location) jdbcTemplate.queryForObject(
				GET_LOCATION_ONUSERID,  new UserLocationRowMapper(), cust);
		
		return location;
	}
}