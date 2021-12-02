package com.niit.MyOnlineBackend.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.Cartline;

public interface CartLineDAO 
{
	public List<Cartline> list(int cartId);
	public Cartline get(int id);	
	public boolean add(Cartline cartLine);
	public boolean update(Cartline cartLine);
	public boolean remove(Cartline cartLine);
	
	// fetch the CartLine based on cartId and productId
	public Cartline getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// list of available cartLine
	public List<Cartline> listAvailable(int cartId);

}
