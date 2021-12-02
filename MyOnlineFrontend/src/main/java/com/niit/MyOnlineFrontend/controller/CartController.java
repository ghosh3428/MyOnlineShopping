package com.niit.MyOnlineFrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.MyOnlineFrontend.service.CartLineService;

@Controller
@RequestMapping(value = "/cart")
public class CartController 
{
	
	@Autowired
	private CartLineService cartLineService;
	

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

		ModelAndView mv = new ModelAndView("page");
		
		if(result != null)
		{
				switch(result) 
				{
					case "added":
						mv.addObject("message", "CartLine has been successfully added!");
						break;
				}
		}
		mv.addObject("title", "Shopping Cart");
		mv.addObject("cartLines", cartLineService.getCartLines());
		mv.addObject("userclickshowcart", true);
		

		
		return mv;

	}

	@RequestMapping("/add/{productId}/product")
	public String addProduct(@PathVariable int productId) 
	{
		String response = cartLineService.addCartLineProduct(productId);
		return "redirect:/cart/show?"+response;
	}

}
