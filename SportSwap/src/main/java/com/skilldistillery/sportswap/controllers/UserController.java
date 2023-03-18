package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.AddressDAO;
import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private AddressDAO addressDAO;

	// directs to home page
	@RequestMapping(path = { "/", "home.do" })
	public String home(Model model, HttpSession session) {
		return "home";
	}

	//logging in
	@RequestMapping(path = "home.do", method = RequestMethod.POST, params = "login")
	public ModelAndView login(@RequestParam("username") String name, @RequestParam("password") String pw,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();

		User user = userDao.login(name, pw);

		if (user != null) {
			// Add user to session to remember who is logged in
			session.setAttribute("loggedInUser", user);
			mv.addObject("user", user);
		} else {

		}
		return mv;
	}

	//logging out
	@RequestMapping(path = "home.do", method = RequestMethod.POST, params = "logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("loggedInUser") != null) {
			// remove user from session and reload
			session.setAttribute("loggedInUser", null);
		} else {

		}
		return mv;
	}
	
	//************* NEEDS TESING AND DAO UPDATES ************
	//for posting account info after entered
	@RequestMapping(path ="createAccount.do", method=RequestMethod.POST)
	public ModelAndView createAccount(
			HttpSession session,
			@RequestParam("username") String name,
			@RequestParam("password") String pw,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("address2") String address2,
			@RequestParam("city") String city,
			@RequestParam("state_province") String state_province,
			@RequestParam("postalCode") String postalCode,
			@RequestParam("country") String country
			) {
		ModelAndView mv = new ModelAndView();
		//create command object
		//--> add user to database
		User newUser = new User();
		newUser.setActive(true);
		newUser.setEmail(email);
		newUser.setUsername(name);
		newUser.setPassword(pw);
		newUser.setRole("ACTIVE_USER");
		
		//use the addressDAO to cretae and add address to database
		//then assign to user
		Address add = new Address();
		add.setStreet(address);
		add.setStreet2(address2);
		add.setCity(city);
		add.setPostalCode(postalCode);
		add.setCity(country);
		add.setCountryCode(country);
		
		add = addressDAO.add(add);
		
		newUser.setUserAddress(add);
		
		newUser = userDao.add(newUser);
		
		//add user to session so that user will be logged in
		session.setAttribute("loggedInUser", newUser);
		mv.setViewName("account");
		return mv;
	}
	
	//for routing to the account creation page
	@RequestMapping(path ="createAccount.do", method=RequestMethod.GET)
	public ModelAndView createAccount() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_account");
		return mv;
	}

}
