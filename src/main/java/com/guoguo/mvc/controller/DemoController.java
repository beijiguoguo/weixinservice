package com.guoguo.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("front/")
public class DemoController {

	@RequestMapping("index")
	public String testEl(Model model){
		model.addAttribute("username","GUOXIAOYONG");
		return "index";
	}
}
