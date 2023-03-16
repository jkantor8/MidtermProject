package com.skilldistillery.sportswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;
	
	
	
	@RequestMapping(path = {"/", "home.do"})
	public String home(Model model) {
		
		User u = new User();
		u.setUsername("admin");
		u.setPassword("admin1");
		u = userDao.login(u);
		model.addAttribute("SMOKETEST", u);
		
		
		
		return "home";
	}
	
}
