package com.niit.MyOnlineFrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.model.Product;

@Controller
@RequestMapping("/json/data")
public class JsonController 
{
		
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllActiveProducts() {
		return productDAO.listActiveProducts();
	}

	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id) {
		return productDAO.listActiveProductsByCategory(id);
	}
	
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {
		return productDAO.productList();
	}
	
	@RequestMapping("/product/ps/products")
	@ResponseBody
	public List<Product> getProductsByParamP() {
		return productDAO.getProductsByParam("purchases",3);
	}
	@RequestMapping("/product/vs/products")
	@ResponseBody
	public List<Product> getProductsByParamV() {
		return productDAO.getProductsByParam("views",3);
	}
}
