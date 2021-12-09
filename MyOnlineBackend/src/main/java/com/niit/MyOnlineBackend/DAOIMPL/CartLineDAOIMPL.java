package com.niit.MyOnlineBackend.DAOIMPL;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.MyOnlineBackend.DAO.CartLineDAO;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.Cartline;
import com.niit.MyOnlineBackend.model.OrderDetails;
@Repository("cartLineDAO")
@Transactional
public class CartLineDAOIMPL implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Cartline getByCartAndProduct(int cartId, int productId) {
		String query = "FROM Cartline WHERE cartId = :cartId AND product.id = :productId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,Cartline.class)
										.setParameter("cartId", cartId)
										.setParameter("productId", productId)
											.getSingleResult();
			
		}catch(Exception ex) {
			return null;	
		}
		
	}

	@Override
	public boolean add(Cartline cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Cartline cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(Cartline cartLine) {	
		try {			
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}


	@Override
	public List<Cartline> list(int cartId) {
		String query = "FROM Cartline WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Cartline.class)
									.setParameter("cartId", cartId)
										.getResultList();		
	}

	@Override
	public Cartline get(int id) {		
		return sessionFactory.getCurrentSession().get(Cartline.class, Integer.valueOf(id));
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<Cartline> listAvailable(int cartId) {
		String query = "FROM Cartline WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Cartline.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}
	
	
	@Override
	public boolean addOrderDetail(OrderDetails orderDetail) {
		try {			
			sessionFactory.getCurrentSession().persist(orderDetail);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

}
