package com.niit.MyOnlineBackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.MyOnlineBackend.DAO.CategoryDAO;
import com.niit.MyOnlineBackend.model.Category;

public class CategoryTest 
{
	
	private static AnnotationConfigApplicationContext context;
	static Category category;
	static CategoryDAO categoryDAO;
	
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.MyOnlineBackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	

	
	@Test
	public void testAddCategory()
	{
		category = new Category();
		category.setCategoryName("Laptop");
		category.setDescription("Sample Laptop Category");
		category.setActive(true);
		
		assertEquals("Error adding category",true,categoryDAO.addCategory(category));
	}

	
	/*
	
	@Test
	public void testAddCategory()
	{
		category = categoryDAO.getCategory(1);
	
		
		assertEquals("Error","TV",category.getCategoryName());
	}
	
	*/
	
	/*
	@Test
	public void testAddCategory()
	{
		category = categoryDAO.getCategory(1);
		category.setCategoryName("Telivision");
	
		
		assertEquals("Error",true,categoryDAO.updateCategory(category));
	}
	*/
	
	/*
	@Test
	public void testAddCategory()
	{
		category = categoryDAO.getCategory(1);
	
		assertEquals("Error",true,categoryDAO.deleteCategory(category));
	}
	
	*/
	/*
	@Test
	public void testAddCategory()
	{
		assertEquals("Error",3,categoryDAO.categoryList().size());
	}
	*/

}
