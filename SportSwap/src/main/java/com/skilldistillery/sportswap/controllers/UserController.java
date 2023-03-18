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

	@RequestMapping(path = { "/", "home.do" })
	public String home(Model model,HttpSession session) {

//		User u = new User();
//		u.setUsername("admin");
//		u.setPassword("admin1");
//		u = userDao.login(u);
//		model.addAttribute("SMOKETEST", u);
//		

		return "home";
	}

	@RequestMapping(path = "home.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String name, 
			@RequestParam("password") String pw,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();

		User user = userDao.login(name, pw);

		if (user != null) {

			// Add user to session to remember who is logged in
			session.setAttribute("loggedInUser", user);
			mv.addObject("user",user);
		} else {
			
		}
		return mv;

	}

}
