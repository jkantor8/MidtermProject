package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.AddressDAO;
import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class AddressController {

	@Autowired
	private AddressDAO addressDAO;

	@GetMapping(path = "address_create.do")
	public ModelAndView routeToCreateAddress(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("address_create");
		return mv;
	}

	@PostMapping(path = "address_create.do")
	public ModelAndView createAddress(HttpSession session, Address address) {
		ModelAndView mv = new ModelAndView();
		// save to session on creation
		session.setAttribute("address", address);
		addressDAO.add(address);
		// change routing depending on context
		String context = session.getAttribute("listing_type").toString();
		if (context.equals("donation")) {
			mv.setViewName("donation_select");
		} else if (context.equals("swap")) {
			mv.setViewName("item_select");
		} else {
			mv.setViewName("home");
		}
		return mv;
	}

	@RequestMapping(path = "address_check.do", method = RequestMethod.POST, params = "which_address")
	public ModelAndView addressOption(HttpSession session, @RequestParam("which_address") String option) {
		ModelAndView mv = new ModelAndView();
		Address address = null;
		String context = session.getAttribute("listing_type").toString();
		if (option.equals("Create a Location")) {
			// go to address creation page
			mv.setViewName("redirect:address_create.do");
		} else if (!option.equals("Create a Location") && (context.equals("swap") || context.equals("donation"))) {
			// check if the user wants to create an item
			address = ((User) session.getAttribute("loggedInUser")).getUserAddress();
			session.setAttribute("address", address);
			mv.setViewName("redirect:item_option.do");
		} 
		return mv;
	}
}
