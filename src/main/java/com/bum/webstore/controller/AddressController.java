package com.bum.webstore.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bum.webstore.domain.RoadAddress;
import com.bum.webstore.service.AddressService;

import lombok.NoArgsConstructor;

/**
 * Servlet implementation class AddressController
 */
@Controller
@RequestMapping("/address")
@NoArgsConstructor
public class AddressController {
	@Autowired
	AddressService addressService;

	@PostMapping("/saveDetailAddr")
	public void saveDetailAddress(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String mgmtNumber = request.getParameter("mgmtNumber");
		String newZipcode = request.getParameter("newZipcode");
		String roadName = request.getParameter("roadName");
		String detailAddr = request.getParameter("detailAddr");
		RoadAddress roadAddress = new RoadAddress(
				mgmtNumber, newZipcode, roadName, detailAddr);
		System.out.println("saving address: " + roadAddress);
		response.sendRedirect("search");
	}

	@GetMapping("/detail")
	public String detailForm(HttpServletRequest request, Model model) {
		String mgmtNumber = request.getParameter("mgmtNumber");
		if (mgmtNumber == null)
			return "addressSearchForm";
		else {
			String newZipcode = request.getParameter("newZipcode");
			String roadName = request.getParameter("roadName");
			RoadAddress roadAddress = new RoadAddress(mgmtNumber, newZipcode,
					roadName, "");
			model.addAttribute("address", roadAddress);
		}
		return "addressDetailForm";
	}

	@GetMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		String key = request.getParameter("addressSearchKey");
		if (key == null)
			return "addressSearchForm";
		else {
			var searchKey = request.getParameter("addressSearchKey");
			var addressList = addressService.searchAddress(searchKey);
			model.addAttribute("addressList", addressList);
		}
		return "addressSearchForm";
//		return "addressSearchResult";
	}
}
