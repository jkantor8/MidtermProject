package com.skilldistillery.sportswap.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.DonationListingDAO;
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.SwapListingDAO;
import com.skilldistillery.sportswap.entities.DonationListing;
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

	// view donations
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
}
