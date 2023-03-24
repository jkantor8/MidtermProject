package com.skilldistillery.sportswap.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.ItemDAO;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class ItemController {

	@Autowired
	private ItemDAO itemDAO;
	
	@GetMapping(path="item_option.do")
	public ModelAndView itemCheck(HttpSession session) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("item_check");
		return mv;
	}

	@RequestMapping(path = "item_option.do", method = RequestMethod.POST, params = "need_item")
	public ModelAndView itemCheckSubmit(HttpSession session, @RequestParam("need_item") String option) {

		ModelAndView mv = new ModelAndView();

		if (option.equals("create a new item")) {
			mv.setViewName("item_create");
		} else {

			mv.setViewName("redirect:item_select.do");
		}
		return mv;
	}

	@PostMapping(path = "item_create.do")
	public String addItem(HttpSession session, Item item, int ageGroupId, int conditionId, int sportId) {
		// need user id for item
		int userId = ((User) session.getAttribute("loggedInUser")).getId();

		Item newItem = itemDAO.add(item, ageGroupId, sportId, conditionId, userId);

		// take user to item select page
		return "redirect:item_select.do";
	}
	
	@GetMapping(path="item_create.do")
	public String addAnotherItem() {
		return "item_create";
	}

	// this is for when the user is trying to select items for a listing
	@GetMapping(path = "item_select.do")
	public ModelAndView showItemsForSelection(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<Item> items = null;
		User user = null;
		// use the logged in users id to filter items
		if (session.getAttribute("loggedInUser") != null) {
			user = (User) session.getAttribute("loggedInUser");
			items = itemDAO.findItemsByUser(user);
		}
		if (items != null && items.size() > 0) {
			mv.addObject("items", items);
			String msg = "Found " + items.size() + " items associated with your acount.";
			mv.addObject("message", msg);
		} else {
			mv.addObject("message", "Sorry, did not find any items associated with your account.");
		}
		mv.setViewName("item_select");
		return mv;
	}
	
	

}