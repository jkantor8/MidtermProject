package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;

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
	public ModelAndView createAccount(HttpSession session, User user) {
		ModelAndView mv = new ModelAndView();
		//create command object
		//--> add user to database
		User newUser = userDao.add(user);
		
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
