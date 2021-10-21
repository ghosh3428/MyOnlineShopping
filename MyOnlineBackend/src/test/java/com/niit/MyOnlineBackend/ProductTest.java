package com.niit.MyOnlineBackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.MyOnlineBackend.DAO.CategoryDAO;
import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.model.Category;
import com.niit.MyOnlineBackend.model.Product;

public class ProductTest 
{
	private static AnnotationConfigApplicationContext context;
	
	static Product product;
	static ProductDAO productDAO;
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.MyOnlineBackend");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	
	@Test
	public void testAddProduct()
	{
		
		product = new Product();
		
		product.setName("boAt Storm Smartwatch ");
		product.setBrand("boAt ");
		product.setDescription("Get ready to keep a track of your fitness and health stats in style with the boAt Storm Smartwatch. Featuring an elegant design, this smartwatch is sure to be your fitness companion at all times.");
		product.setUnitPrice(1999);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(1);
		product.setQuantity(5);
		
		assertEquals("Something went wrong while inserting a new product!",
				true,productDAO.addProduct(product));
		
	}
	
	
	/*
	@Test
	public void testGetProduct()
	{
		product = productDAO.getProduct(1);
		
		assertEquals("Error fetching product","Oppo Selfie S53",product.getName());
	}
	*/
	/*
	@Test
	public void testUpdateProduct()
	{
		product = productDAO.getProduct(1);
		product.setQuantity(15);
		
		assertEquals("Errorupdating product",true,productDAO.updateProduct(product));
	}
	*/
	
}
