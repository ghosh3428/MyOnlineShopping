package com.niit.MyOnlineFrontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.niit.MyOnlineBackend.DAO.CategoryDAO;
import com.niit.MyOnlineBackend.DAO.ProductDAO;
import com.niit.MyOnlineBackend.model.Category;
import com.niit.MyOnlineBackend.model.Product;
import com.niit.MyOnlineFrontend.util.FileUploader;

@Controller
@RequestMapping(value = "/manage")
public class ManageProductController {
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping("/product")
	public ModelAndView manageProduct(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Product");
		mv.addObject("userclickmanageproduct", true);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully!");
			}

			if (operation.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}

		Product newProduct = new Product();
		newProduct.setActive(true);
		newProduct.setSupplierId(1);

		mv.addObject("product", newProduct);
		return mv;

	}

	@RequestMapping("/{id}/product")
	public ModelAndView viewProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Product");
		mv.addObject("userclickmanageproduct", true);

		Product newProduct = productDAO.getProduct(id);
		mv.addObject("product", newProduct);
		return mv;

	}

	@ModelAttribute("categories")
	public List<Category> modelCategories() {
		return categoryDAO.categoryList();
	}

	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}

	@RequestMapping(value = "/manage/product", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product newProduct, BindingResult results,
			Model model, HttpServletRequest request)

	{
		if (results.hasErrors()) {

			model.addAttribute("userclickmanageproduct", true);
			model.addAttribute("title", "Product Management");
			return "page";
		}

		if (newProduct.getId() == 0) {
			productDAO.addProduct(newProduct);
		} else {
			productDAO.updateProduct(newProduct);
		}

		if (!newProduct.getFile().getOriginalFilename().equals("")) {
			FileUploader.uploadFile(request, newProduct.getFile(), newProduct.getCode());
		}

		return "redirect:/manage/product?operation=product";
	}

	@RequestMapping(value = "/add/category", method = RequestMethod.POST)
	public String handlecategorySubmission(@ModelAttribute("category") Category newcategory, BindingResult results) {
		categoryDAO.addCategory(newcategory);

		return "redirect:/manage/product?operation=category";
	}

	@RequestMapping(value = "/product/{id}/activation", method = {RequestMethod.POST })
	@ResponseBody
	public String handleProductAvtivation(@PathVariable int id) {
		Product product = productDAO.getProduct(id);

		boolean isActive = product.isActive();

		product.setActive(!isActive);

		productDAO.updateProduct(product);

		return (isActive) ? "Successfully Deactivated the product with id : " + product.getId()
				: "Successfully Activated the product with id : " + product.getId();
	}

}
