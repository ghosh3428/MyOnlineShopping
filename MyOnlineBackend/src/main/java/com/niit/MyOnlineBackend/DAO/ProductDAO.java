package com.niit.MyOnlineBackend.DAO;

import java.util.List;

import com.niit.MyOnlineBackend.model.Product;

public interface ProductDAO 
{
	Product getProduct(int productId);
	List<Product> productList();	
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
	List<Product> listActiveProducts();	
	List<Product> listActiveProductsByCategory(int categoryId);

	List<Product> getProductsByParam(String param, int count);	
}
