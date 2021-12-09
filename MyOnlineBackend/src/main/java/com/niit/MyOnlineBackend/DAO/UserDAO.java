package com.niit.MyOnlineBackend.DAO;

import java.util.List;

import com.niit.MyOnlineBackend.model.Address;
import com.niit.MyOnlineBackend.model.Cart;
import com.niit.MyOnlineBackend.model.User;

public interface UserDAO {
	boolean addUser(User user);

	User getByEmail(String email);

	boolean addAddress(Address address);

	boolean updateCart(Cart cart);

	Address getBillingAddress(int userId);

	List<Address> listShippingAddresses(int userId);

	Address getAddress(int addressId);

}
