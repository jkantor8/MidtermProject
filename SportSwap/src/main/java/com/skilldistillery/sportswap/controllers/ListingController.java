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

import com.skilldistillery.sportswap.data.AddressDAO;
import com.skilldistillery.sportswap.data.AgeGroupDAO;
import com.skilldistillery.sportswap.data.ConditionDAO;
import com.skilldistillery.sportswap.data.DonationListingDAO;
import com.skilldistillery.sportswap.data.ItemDAO;
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.SportDAO;
import com.skilldistillery.sportswap.data.SwapListingDAO;
import com.skilldistillery.sportswap.entities.AgeGroup;
import com.skilldistillery.sportswap.entities.Condition;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.SwapListing;

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
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private ConditionDAO conditionDAO;
	@Autowired
	private SportDAO sportDAO;
	@Autowired
	private AgeGroupDAO ageGroupDAO;

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

	// ******************* LISTING CREATION ************
	//page direct
//	@RequestMapping(path = "create_listing.do", method = RequestMethod.GET)
//	public ModelAndView loadCreateListing(HttpSession session) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("create_listing");
//		return mv;
//	}
	
	@RequestMapping(path = "create_listing.do", method = RequestMethod.GET, params="create_listing")
	public ModelAndView createListing(
			HttpSession session,
			@RequestParam(name="create_listing",defaultValue="swap") String listingType) {
		ModelAndView mv = new ModelAndView();
		
		String msg ="";
		//test
		if(listingType.equals("swap")) {
			msg = "create_swap.jsp";
			
		}
		else if(listingType.equals("donation")) {
			msg = "create_donation.jsp";
		}
		else if(listingType.equals("sale")) {
			msg = "create_sale.jsp";
		}
		else {
			msg="Sorry, there was a problem.";
		}
		mv.addObject("testMsg", msg);
		mv.setViewName("create_listing");
		return mv;
	}
	
	//this method assumes the user is always creating a new item
	@PostMapping(path="create_listing.do")
	public ModelAndView createItem(SwapListing swapListing, HttpSession httpsession,
			@RequestParam(name="age_group_id",defaultValue="1")int ageGroupId,
			@RequestParam("useraddress") int addressId,
			@RequestParam("condition_id") int conditionId,
			@RequestParam("sport_id") int sportId) {
		
		
		
//		Address address = addressDAO.findById(addressId);
//		Condition condition = conditionDAO.findById(conditionId);
//		AgeGroup ageGroup = ageGroupDAO.findById(ageGroupId);
//		//test
//		Sport sport = sportDAO.findById(1);
//		
//		session.setAttribute("CreatedItemId", itemId);
//		item = itemDAO.add(item, ageGroup,condition,sport);
		
		
//		SwapListing swapListing=SwapListingDAO()
		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
//	@RequestMapping(path= "createItem.do")
//	public String createItem() {
//		return "createItem";
//	
//	
//	}
	
//	@RequestMapping(path="addItem.do", method = RequestMethod.POST, params="listing_type" )
//	public String addItem(HttpSession httpsession, Item item, int ageGroupId, int conditionId, int sportId ) {
//		Sport sport = sportDAO.findById(sportId);
//		Condition condition = conditionDAO.findById(conditionId);
//		AgeGroup ageGroup = ageGroupDAO.findById(ageGroupId);
//		Item newItem = ItemDAO.add(ageGroup, condition, sport);
//			httpsession.setAttribute("Item", newItem);
//			
//			
//			return "donation_create";
//	}

	@RequestMapping(path="create_listing.do", method = RequestMethod.GET, params="listing_type" )
	public String routeTest(HttpSession httpsession, @RequestParam("listing_type") String type) {
		httpsession.setAttribute("listing_type", type);
		if (type.equals("donation")) {
			return "address_check";
		} else if (type.equals("swap")) {
			
			return "address_check";
		} else if (type.equals("sale")) {
			return "item_check";
		} else {
			return "home";
		}
			
		
	}
	
	
	
	
	
	
}
