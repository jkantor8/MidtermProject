package com.skilldistillery.sportswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skilldistillery.sportswap.data.UserDAO;

public class ItemController {

	@Controller
	public class UserController {

		@Autowired
		private UserDAO userDao;
	}
}