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
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.SwapListingDAO;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.SwapListing;

@Controller
public class ListingController {

	@Autowired
	private SwapListingDAO swapListingDAO;
	@Autowired
	private DonationListingDAO donationListingDAO;
	@Autowired
	private SaleListingDAO saleListingDAO;

	// directs to page
	@GetMapping(path = { "listings.do" })
	public ModelAndView listings() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listings");
		return mv;
	}

	// view swaps
	// use session for saving listings looked at?
	@RequestMapping(path = "listings.do", method = RequestMethod.POST, params = "swap_listings")
	public ModelAndView viewSwaps(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String noswaps = "Sorry, no swap listings matching those criteria were found.";

		List<SwapListing> swaps = swapListingDAO.getAllSwapListings();

		if (swaps != null && swaps.size() > 0) {
			// hand a list of swap listings to the page

			mv.addObject("listings", swaps);
		} else {
			mv.addObject("message", noswaps);
		}
		return mv;
	}

	// view donations
	// use session for saving listings looked at?
	@RequestMapping(path = "listings.do", method = RequestMethod.POST, params = "donation_listings")
	public ModelAndView viewDonations(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String noDonations = "Sorry, no donation listings matching those criteria were found.";

		List<DonationListing> donations = donationListingDAO.getAllDonationListings();

		if (donations != null && donations.size() > 0) {
			// hand a list of swap listings to the page

			mv.addObject("listings", donations);
		} else {
			mv.addObject("message", noDonations);
		}
		return mv;
	}

	// view sales
	// use session for saving listings looked at?
	@RequestMapping(path = "listings.do", method = RequestMethod.POST, params = "sale_listings")
	public ModelAndView viewSales(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String noSales = "Sorry, no sales listings matching those criteria were found.";

		List<SaleListing> sales = saleListingDAO.getAllSaleListings();

		if (sales != null && sales.size() > 0) {
			// hand a list of swap listings to the page

			mv.addObject("listings", sales);
		} else {
			mv.addObject("message", noSales);
		}
		return mv;
	}

	// *********** CREATE LISTINGS MAPPINGS
	// *********************************************************

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

	@RequestMapping(path="address_check.do", method=RequestMethod.GET, params="which_address")
	public ModelAndView addressOption(HttpSession session, @RequestParam("which_address") String option) {
		ModelAndView mv = new ModelAndView();
		String context = session.getAttribute("listing_type").toString();
		if(option.equals("Create a Location")) {
			//go to address creation page
			mv.setViewName("address_create");
		}
		else if(!option.equals("Create a Location") && context.equals("swap")) {
			//check if the user wants to create an item
			mv.setViewName("item_check");
		}
		else if(!option.equals("Create a Location") && context.equals("donation"))
			//take user to the donation creation page
			mv.setViewName("donation_create");
		return mv;
	}

	// NEED DAO METHODS FOR THIS
	@GetMapping(path = "donation_create.do")
	public ModelAndView createDonation(HttpSession httpsession, DonationListing donationListing) {
		ModelAndView mv = new ModelAndView();
		
		DonationListing createdDonationListing = donationListingDAO.add(donationListing, donationListing.getItems(), donationListing.getDonationAddress().getId());
		mv.addObject("listings", createdDonationListing);
		mv.setViewName("listings");
		return mv;
	}
	
	@GetMapping(path="swap_create.do")
	public ModelAndView createSwap(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//List of items to add
		List<String> itemIds = (List<String>)session.getAttribute("itemsForListing");
		//swapListing=swapListingDAO.add(swapListing, null, 0);
		mv.addObject("test",itemIds);
		mv.setViewName("swap_create");
		return mv;
	}
	
	@PostMapping(path="swap_create.do")
	
	//after item select, this mapping will save the list of item ids to a string in the session
	//and route the user to the correct listing creation page
	@PostMapping(path="finish_listing.do", params="selectable_item")
	public ModelAndView finishListing(
			HttpSession session,
			@RequestParam("selectable_item")List<String> selectable_items) {
		//save ITEM IDs in the session
		session.setAttribute("itemsForListing",selectable_items);
		ModelAndView mv = new ModelAndView();
		
		//finish listing based on context and items
		String context = session.getAttribute("listing_type").toString();
		switch(context) {
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
