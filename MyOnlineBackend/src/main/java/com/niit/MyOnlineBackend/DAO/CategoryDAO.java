package com.niit.MyOnlineBackend.DAO;

import java.util.List;

import com.niit.MyOnlineBackend.model.Category;


public interface CategoryDAO 
{
	Category getCategory(int id);
	List<Category> categoryList();
	boolean addCategory(Category category);
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);

}
