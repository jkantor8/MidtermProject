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

}
