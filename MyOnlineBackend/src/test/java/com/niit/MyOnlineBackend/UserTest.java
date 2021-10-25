package com.niit.MyOnlineBackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.MyOnlineBackend.DAO.UserDAO;
import com.niit.MyOnlineBackend.model.User;


public class UserTest 
{

	private static AnnotationConfigApplicationContext context;
	static User user;
	static UserDAO userDAO;
	
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.MyOnlineBackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddUser()
	{
		
		user = new User();
		user.setFirstName("Punkaj");
		user.setLastName("Singh");
		user.setEmail("ps@gmail.com");
		user.setContactNumber("1234567890");
		user.setRole("SUPPLIER");
		user.setPassword("pass");
		assertEquals("Error",true,userDAO.addUser(user));
		
		user = new User();
		user.setFirstName("Sumit Kumar");
		user.setLastName("Das");
		user.setEmail("skd@gmail.com");
		user.setContactNumber("1234567890");
		user.setRole("SUPPLIER");
		user.setPassword("pass");
		assertEquals("Error",true,userDAO.addUser(user));
		/*
		user = new User();
		user.setFirstName("Niit");
		user.setLastName("Jadavpur");
		user.setEmail("niitjdv@gmail.com");
		user.setContactNumber("1234567890");
		user.setRole("ADMIN");
		user.setPassword("admin12345");
		
		
		
		
		assertEquals("Error",true,userDAO.addUser(user));
		*/
	}

}
