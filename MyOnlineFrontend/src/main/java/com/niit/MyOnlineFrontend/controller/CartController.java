package com.niit.MyOnlineFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@RequestMapping("/show")
	public ModelAndView showCart() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Shopping Cart");
		mv.addObject("userclickshowcart", true);
		return mv;

	}

}
