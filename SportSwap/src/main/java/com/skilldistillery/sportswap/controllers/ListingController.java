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

import com.skilldistillery.sportswap.data.DonationListingDAO;
import com.skilldistillery.sportswap.data.ItemDAO;
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.SwapListingDAO;
import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class ListingController {

	@Autowired
	private SwapListingDAO swapListingDAO;
	@Autowired
	private DonationListingDAO donationListingDAO;
	@Autowired
	private SaleListingDAO saleListingDAO;
	@Autowired
	private ItemDAO itemDAO;

	// directs to page
	@GetMapping(path = { "listings.do" })
	public ModelAndView listings() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listings");
		return mv;
	}

	// view swaps
	// use session for saving listings looked at?
	@RequestMapping(path = "listings.do", method = RequestMethod.POST, params = "list_view")
	public ModelAndView viewSwaps(HttpSession session, @RequestParam("list_view") String listView) {
		ModelAndView mv = new ModelAndView();
		
		//set a session variable to tell single listing view page what type to look for 
		//which dao to use
		

		String noListings = "Sorry, no listings matching those criteria were found.";

		switch (listView) {
		case "view donations":
			List<DonationListing> donList = donationListingDAO.getAllDonationListings();
			//session.setAttribute("singleListViewType", "donationView");
			mv.addObject("listings", donList);
			break;
		case "view sales":
			List<SaleListing> saleList = saleListingDAO.getAllSaleListings();
			//session.setAttribute("singleListViewType", "saleView");
			mv.addObject("listings", saleList);
			break;
		case "view swaps":
			List<SwapListing> swapList = swapListingDAO.getAllSwapListings();
			//session.setAttribute("singleListViewType", "swapView");
			mv.addObject("listings", swapList);
			break;
		default:
			break;
		}
		return mv;
	}

	// *********** CREATE LISTINGS MAPPINGS

	@RequestMapping(path = "create_listing.do", method = RequestMethod.GET, params = "listing_type")
	public String routeTest(HttpSession session, @RequestParam("listing_type") String type) {
		session.setAttribute("listing_type", type);
		if (type.equals("donation") || type.equals("swap")) {
			return "address_check";
		} else if (type.equals("sale")) {
			return "item_check";
		} else {
			return "home";
		}
	}

//	@RequestMapping(path = "donation_create.do")
//	public ModelAndView createDonation(HttpSession httpsession, DonationListing donationListing) {
//		ModelAndView mv = new ModelAndView();
//
//		DonationListing createdDonationListing = donationListingDAO.add(donationListing, donationListing.getItems(),
//				donationListing.getDonationAddress().getId());
//		mv.addObject("listings", createdDonationListing);
//		mv.setViewName("listings");
//		return mv;
//	}

	// SWAP
	@GetMapping(path = "swap_create.do")
	public ModelAndView routeToCreateSwap(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemsToAdd", session.getAttribute("items"));
		mv.setViewName("swap_create");
		return mv;
	}

	@PostMapping(path = "submit_swap.do")
	public ModelAndView createSwap(HttpSession session, SwapListing listing) {
		ModelAndView mv = new ModelAndView();
		
		List<Item> items = (List<Item>) session.getAttribute("items");
		Address address = (Address) session.getAttribute("address");
		User user = (User) session.getAttribute("loggedInUser");

		SwapListing createdSwapListing = swapListingDAO.add(listing, items, address,user);
		mv.setViewName("listings");
		return mv;
	}

	// after item select, this mapping will save the list of item ids to a string in
	// the session
	// and route the user to the correct listing creation page
	@PostMapping(path = "finish_listing.do", params = "selectable_item")
	public ModelAndView finishListing(HttpSession session,
			@RequestParam("selectable_item") List<String> selectable_items) {
		// save ITEMs selected in the session
		List<Item> items = itemDAO.findItemsByIds(selectable_items);
		session.setAttribute("items", items);
		ModelAndView mv = new ModelAndView();

		// finish listing based on context and items
		String context = session.getAttribute("listing_type").toString();
		switch (context) {
		case "swap":
			mv.setViewName("redirect:swap_create.do");
			break;
		case "donation":
			mv.setViewName("redirect:donation_create.do");
			break;
		case "sale":
			mv.setViewName("redirect:sale_create.do");
			break;
		default:
			mv.setViewName("redirect:home.do");
			break;
		}

		return mv;
	}

}
