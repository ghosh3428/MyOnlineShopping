package com.niit.MyOnlineFrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.MyOnlineBackend.DAO.CategoryDAO;

@Controller
@RequestMapping(value="/product/show")
public class ProductController 
{
	@Autowired
	CategoryDAO categoryDAO;	

	@RequestMapping(value="/all/products")
	public ModelAndView allProducts()
	{
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","All Products");
		mv.addObject("userclickallproducts" , true);
		mv.addObject("categories",categoryDAO.categoryList());
		
		return mv;
	}
	
	@RequestMapping(value="/category/{id}/products")
	public ModelAndView categoryProduct(@PathVariable("id") int c_id)
	{
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","Category Products");
		mv.addObject("userclickcategoryproducts" , true);
		mv.addObject("category",categoryDAO.getCategory(c_id));
		mv.addObject("categories",categoryDAO.categoryList());
		
		return mv;
	}
}
