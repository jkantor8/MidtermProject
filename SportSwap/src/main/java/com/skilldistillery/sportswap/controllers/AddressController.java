package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.AddressDAO;
import com.skilldistillery.sportswap.entities.Address;

@Controller
public class AddressController {

	@Autowired
	private AddressDAO addressDAO;

	@PostMapping(path = "address_create.do")
	public ModelAndView createAddress(HttpSession session, Address address) {
		ModelAndView mv = new ModelAndView();
		//save to session on creation
		session.setAttribute("address", address);
		addressDAO.add(address);
		// change routing depending on context
		String context = session.getAttribute("listing_type").toString();
		if (context.equals("donation")) {
			mv.setViewName("donation_create");
		} else if (context.equals("swap")) {
			mv.setViewName("swap_create");
		} else {
			mv.setViewName("home");
		}
		return mv;
	}

}
