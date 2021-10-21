package com.niit.MyOnlineBackend.DAO;

import java.util.List;

import com.niit.MyOnlineBackend.model.User;



public interface UserDAO 
{
	boolean addUser(User user);
	User getByEmail(String email) ;
}
