package com.niit.MyOnlineFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontController 
{

	@RequestMapping(value="/")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","My Online Shopping");
		mv.addObject("userclickhome" , true);
		
		return mv;
	}
	
	@RequestMapping(value="/aboutus")
	public ModelAndView aboutus()
	{
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","My Online Shopping");
		mv.addObject("userclickaboutus",true);
		
		return mv;
	}
}
