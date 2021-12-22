package com.niit.MyOnlineFrontend.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.niit.MyOnlineBackend.DAO.CartLineDAO;
import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.DAO.UserDAO;
import com.niit.MyOnlineBackend.model.Address;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.Cartline;
import com.niit.MyOnlineBackend.model.OrderDetails;
import com.niit.MyOnlineBackend.model.OrderItems;
import com.niit.MyOnlineBackend.model.Product;
import com.niit.MyOnlineBackend.model.User;
import com.niit.MyOnlineFrontend.model.CheckoutModel;
import com.niit.MyOnlineFrontend.model.UserModel;

@Component("checkoutHandler")
public class CheckOutHandler {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;

	private HttpSession session;

	public List<Address> getShippingAddresses(CheckoutModel model) {
		List<Address> addresses = userDAO.listShippingAddresses(model.getUser().getId());
		if (addresses.size() == 0) {
			addresses = new ArrayList<>();
		}
		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser().getId()));
		return addresses;
	}

	public CheckoutModel init(String name) throws Exception {

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;

		if (user != null) {
			checkoutModel = new CheckoutModel();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCart());

			double checkoutTotal = 0.0;
			List<Cartline> cartLines = cartLineDAO.listAvailable(user.getCart().getId());

			if (cartLines.size() == 0) {
				throw new Exception("There are no products available for checkout now!");
			}

			for (Cartline cartLine : cartLines) {
				checkoutTotal += cartLine.getTotal();
			}

			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);
		}
		return checkoutModel;
	}

	public String saveAddressSelection(CheckoutModel checkoutModel, int shippingId) {

		String transitionValue = "success";
		Address shipping = userDAO.getAddress(shippingId);
		checkoutModel.setShipping(shipping);
		return transitionValue;

	}

	public String saveOrder(CheckoutModel checkoutModel) {
		String transitionValue = "success";

		// create a new order object
		OrderDetails orderDetail = new OrderDetails();

		// attach the user
		orderDetail.setUser(checkoutModel.getUser());

		// attach the shipping address
		orderDetail.setShipping(checkoutModel.getShipping());

		// fetch the billing address
		Address billing = userDAO.getBillingAddress(checkoutModel.getUser().getId());
		orderDetail.setBilling(billing);

		List<Cartline> cartLines = checkoutModel.getCartLines();
		OrderItems orderItem = null;

		double orderTotal = 0.0;
		int orderCount = 0;
		Product product = null;

		try {
			for (Cartline cartLine : cartLines) {

				orderItem = new OrderItems();

				orderItem.setBuyingPrice(cartLine.getBuyingPrice());
				orderItem.setProduct(cartLine.getProduct());
				orderItem.setProductCount(cartLine.getProductCount());
				orderItem.setTotal(cartLine.getTotal());

				orderItem.setOrderDetail(orderDetail);
				orderDetail.getOrderItems().add(orderItem);

				orderTotal += orderItem.getTotal();
				orderCount++;

				// update the product
				// reduce the quantity of product
				product = cartLine.getProduct();
				product.setQuantity(product.getQuantity() - cartLine.getProductCount());
				product.setPurchases(product.getPurchases() + cartLine.getProductCount());
				productDAO.updateProduct(product);

				// delete the cartLine
				cartLineDAO.remove(cartLine);

			}

			orderDetail.setOrderTotal(orderTotal);
			orderDetail.setOrderCount(orderCount);
			orderDetail.setOrderDate(new Date());

			// save the order
			cartLineDAO.addOrderDetail(orderDetail);

			// set it to the orderDetails of checkoutModel
			checkoutModel.setOrderDetail(orderDetail);

			// add the cart details to cart history

			// update the cart
			Cart cart = checkoutModel.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
			cart.setCartLines(cart.getCartLines() - orderCount);
			cartLineDAO.updateCart(cart);

			// update the cart if its in the session

			UserModel um = (UserModel) session.getAttribute("userModel");
			if (um != null) {
				um.setCart(cart);
			}
			return transitionValue;
		} catch (Exception e) {
			e.printStackTrace();
			return transitionValue;
		}

	}

	public OrderDetails getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}

	public String saveAddress(CheckoutModel checkoutModel, Address shipping) {

		String transitionValue = "success";

		// set the user id
		// set shipping as true
		shipping.setUserId(checkoutModel.getUser().getId());
		shipping.setShipping(true);
		userDAO.addAddress(shipping);

		// set the shipping id to flowScope object
		checkoutModel.setShipping(shipping);

		return transitionValue;
	}
}
