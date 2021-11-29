package com.niit.MyOnlineFrontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.niit.MyOnlineBackend.DAO.UserDAO;
import com.niit.MyOnlineBackend.model.User;
import com.niit.MyOnlineFrontend.model.UserModel;

@ControllerAdvice
public class GlobalController 
{
	
	@Autowired
	private HttpSession session;
	
	
	private User user;
	private UserModel userModel;
	
	@Autowired
	private UserDAO userDAO;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() 
	{
		if(session.getAttribute("userModel")== null ) 
		{
			//add a new UserModel
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			user = userDAO.getByEmail(authentication.getName());
			
			if(user!=null)
			{
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				
				if(userModel.getRole().equals("USER"))
				{
					userModel.setCart(user.getCart());
				}
				
				session.setAttribute("userModel",userModel );
				return userModel;
			}
			
		}
		
		//return the UserModel if it is already present
		return (UserModel) session.getAttribute("userModel");
	
	}
}
