package com.niit.MyOnlineFrontend.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.MyOnlineBackend.DAO.CartLineDAO;
import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.Cartline;
import com.niit.MyOnlineBackend.model.Product;
import com.niit.MyOnlineFrontend.model.UserModel;

@Service("cartLineService")
public class CartLineService 
{
	
	@Autowired
	private HttpSession session;

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	ProductDAO productDAO;
	
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	public List<Cartline> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}
	
	public String addCartLineProduct(int productId) {

		Cart cart = this.getCart();
		
		Cartline cartLine = new Cartline();
		
		Product product = productDAO.getProduct(productId);
		
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice());

		// insert a new cartLine
		cartLineDAO.add(cartLine);

		// update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		cartLineDAO.updateCart(cart);

		String response = "result=added";
		
		return response;

	}

}
