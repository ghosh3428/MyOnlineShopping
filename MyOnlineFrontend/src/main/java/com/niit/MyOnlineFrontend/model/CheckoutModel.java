package com.niit.MyOnlineFrontend.model;

import java.io.Serializable;
import java.util.List;

import com.niit.MyOnlineBackend.model.Address;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.Cartline;
import com.niit.MyOnlineBackend.model.OrderDetails;
import com.niit.MyOnlineBackend.model.User;

public class CheckoutModel implements Serializable {


	private static final long serialVersionUID = 1L;

	private User user;
	private Address shipping;
	private Cart cart;
	private List<Cartline> cartLines;
	private OrderDetails orderDetail;
	private double checkoutTotal;

	public OrderDetails getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetails orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getCheckoutTotal() {
		return checkoutTotal;
	}

	public void setCheckoutTotal(double checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}

	public List<Cartline> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<Cartline> cartLines) {
		this.cartLines = cartLines;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getShipping() {
		return shipping;
	}

	public void setShipping(Address shipping) {
		this.shipping = shipping;
	}
	
}
