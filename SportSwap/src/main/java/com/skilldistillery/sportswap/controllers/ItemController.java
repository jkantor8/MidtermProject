package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.ItemDAO;
import com.skilldistillery.sportswap.entities.Item;

@Controller
public class ItemController {

	@Autowired
	private ItemDAO itemDAO;

	@RequestMapping(path = "item_check.do", method = RequestMethod.GET, params = "need_item")
	public ModelAndView itemCheck(HttpSession session, @RequestParam("need_item") String option) {
		
		ModelAndView mv = new ModelAndView();
		
		if (option.equals("create a new item")) {
			mv.setViewName("item_create");
		} else {

			mv.setViewName("item_select");
		}
		return mv;
	}

	@PostMapping(path = "item_create.do")
	public String addItem(HttpSession session, Item item, int ageGroupId, int conditionId, int sportId) {

		Item newItem = itemDAO.add(item, ageGroupId, conditionId, sportId);
		session.setAttribute("Item", newItem);

		String context = session.getAttribute("listing_type").toString();

		if (context.equals("swap")) {
			return "swap_create";
		} else {
			return "sale_create";
		}

	}

}