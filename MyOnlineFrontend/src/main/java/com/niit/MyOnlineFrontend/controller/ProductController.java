package com.niit.MyOnlineFrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.MyOnlineBackend.DAO.CategoryDAO;
import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.model.Product;

@Controller
@RequestMapping(value="/product/show")
public class ProductController 
{
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;

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
	
	@RequestMapping(value = "/{id}/product")
	public ModelAndView showSingleProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.getProduct(id);
		product.setViews(product.getViews() + 1);
		
		productDAO.updateProduct(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);

		mv.addObject("userclicksingleproduct", true);

		return mv;
	}
}
