/* 
 * Author ::. Sivateja Kandula | www.java4s.com 
 *
 */

package com.location.app.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gscquartz.HelloJob;
import com.java4s.app.repository.SpringJava4sDAO;
import com.java4s.model.CustomException;
import com.java4s.model.location;

@RestController
public class SpringJava4sController {
	
	@Autowired
	public SpringJava4sDAO dao;

	
	public static void main(String[] args) throws SchedulerException {
		
		
	}
	
	@RequestMapping("/say")
	public String sayHello() throws SchedulerException {
		
//		JobDetail job = JobBuilder.newJob(HelloJob.class).build();
//		
//		Trigger t1 =  TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
//		
//		@SuppressWarnings("static-access")
//		Scheduler sc = new StdSchedulerFactory().getDefaultScheduler();
//		
//		sc.start();
//		sc.scheduleJob(job,t1);
//		
		
		
		return "Hello";
		
	}

	@RequestMapping("/getcustLocation")
	public List<location> customerLocation() {
		
		List<location> customers = dao.isData();		 
		
		return customers;
	}
	
	@PostMapping("/saveLocation")
	public JSONObject saveLocation(@RequestBody location location) {	
		System.out.println("entering "+this.getClass().getName());
		System.out.println("loca details ........"+location.getCreatedTime()+" user id is ==="+location.getUserId());
		
		if(!(location.getUserId()==null)&!location.getUserId().matches("[0-9]+")) {
			throw new CustomException("User id cannot be null or a string", "Failed");
		}
//		int row = dao.saveLocation(location);
			JSONObject json = new JSONObject(); 
		int row = 1;
		
		if(row>0) {
			json.put("statusCode", "0");
			json.put("message", "success");
		}else{
			json.put("statusCode", "1");
			json.put("message", "Failed");
		}
		return json;
	}
	
	
	
	
	
	@RequestMapping(value = "/retrieveLocById/{id}")
	@ResponseBody
	public location sendLocationbasedOnId(@PathVariable("id") String id) {
		System.out.println("entering "+this.getClass().getName());
		
		location location= dao.findByCustomerId(id);
	    return location;
	}
	
	
}

//URL: http://localhost:8080/springbootds/getcustInfo