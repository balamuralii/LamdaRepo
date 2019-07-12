package com.location.app.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java4s.app.repository.SpringJava4sDAO;
import com.java4s.model.location;

@XmlAccessorType
@XmlRootElement(name = "location")
@Path("/users")
public class UserResource
{
    //private static Map<Integer, User> DB = new HashMap<>();
	
	
	@Autowired
	public SpringJava4sDAO dao;
     
    @GET
    @Produces("application/json")
    public List<location> getAllUsers() {
    	System.out.println("called");
    	location users = new location();
        List<location> location = dao.isData();
        return location;
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
    
    
    
    
}}