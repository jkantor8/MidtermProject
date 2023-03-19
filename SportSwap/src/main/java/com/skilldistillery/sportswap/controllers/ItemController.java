package com.skilldistillery.sportswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skilldistillery.sportswap.data.UserDAO;

@Controller
public class ItemController {

	@Autowired
	private UserDAO userDao;

}