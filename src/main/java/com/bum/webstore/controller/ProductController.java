package com.bum.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bum.webstore.domain.repository.ProductRepository;
import com.bum.webstore.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products") 
	public String list(Model model, HttpServletRequest request) {
		var productList = productService.getAllProducts();
		model.addAttribute("products", productList);
		request.setAttribute("tagline", "이 세상 유일 원가 공개 수제 비누");
		return "products";
	}
	
	@GetMapping("/update/stock") 
	public String updateStock(Model model, RedirectAttributes redAttrs) {
		var rowCount = productService.updateProductStock();	
		redAttrs.addFlashAttribute("updatedRowCount", rowCount);
		var productList = productService.getAllProducts();
		model.addAttribute("products", productList);
		return "redirect:/products";
	}	
}
