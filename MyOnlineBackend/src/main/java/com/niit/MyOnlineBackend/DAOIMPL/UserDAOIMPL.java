package com.niit.MyOnlineBackend.DAOIMPL;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.MyOnlineBackend.DAO.UserDAO;
import com.niit.MyOnlineBackend.model.Address;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.User;


@Repository("userDAO")
@Transactional
public class UserDAOIMPL implements UserDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	
	public boolean addUser(User user) 
	{
		try
		{
			user.setEnabled(true);
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) 
	{
		String selectQuery = "FROM User WHERE email = :email";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,User.class)
						.setParameter("email",email)
							.getSingleResult();
		}
		
		catch(Exception ex) 
		{
			return null;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try
		{
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) 
	{
		try 
		{			
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception ex) 
		{		
			ex.printStackTrace();
			return false;	
		}		
	}

}
