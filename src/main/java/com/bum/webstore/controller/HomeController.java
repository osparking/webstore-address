package com.bum.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("greeting", "범이비누에오신것을 환영합니다.");
		model.addAttribute("tagline", "세상에서유일한원가공개 수제비누");
		return "welcome";
	}

}
