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
		String response = null;
		Cartline cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null)
		{
			Product product = productDAO.getProduct(productId);
			cartLine = new Cartline();
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

		    response = "result=added";
			
		}
		else
		{
			if(cartLine.getProductCount()<5)
			{
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}
			else
			{
				response="result=maximum";	
			}
		}
		
		return response;

	}
	
	public String manageCartLine(int cartLineId, int count) {

		Cartline cartLine = cartLineDAO.get(cartLineId);

		double oldTotal = cartLine.getTotal();

		Product product = cartLine.getProduct();

		// check if that much quantity is available or not
		if (product.getQuantity() < count) {
			return "result=unavailable";
		}

		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartLineDAO.update(cartLine);

		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCart(cart);

		return "result=updated";
	}
	
	public String removeCartLine(int cartLineId) {

		Cartline cartLine = cartLineDAO.get(cartLineId);
		// deduct the cart
		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		cartLineDAO.updateCart(cart);

		// remove the cartLine
		cartLineDAO.remove(cartLine);

		return "result=deleted";
	}

}
