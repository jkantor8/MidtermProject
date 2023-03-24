package com.skilldistillery.sportswap.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.DonationListingDAO;
import com.skilldistillery.sportswap.data.PostDAO;
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.SwapListingDAO;
import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Post;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class PostController {

	@Autowired
	private PostDAO postDAO;
	@Autowired
	private SwapListingDAO swapListingDAO;
	@Autowired
	private DonationListingDAO donationListingDAO;
	@Autowired
	private SaleListingDAO saleListingDAO;
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(path = "makePost.do", method = RequestMethod.POST)
	public ModelAndView createPost(Post post, String comment, int listingId, String commentType, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("loggedInUser");
		Post createdPost = null;
		post.setComment(comment);
		switch (commentType) {
		
		case "Respond to Donation":
			
			DonationListing donationListing = donationListingDAO.findById(listingId);
			createdPost = postDAO.addToDonate(post, user, donationListing);
			mv.addObject("post", createdPost);
			mv.addObject("listing",donationListing);
			mv.setViewName("singleDonation");
			break;
		case "Reply to Seller":
			SaleListing saleListing = saleListingDAO.findById(listingId);
			createdPost = postDAO.addToSale(post, user, saleListing);
			mv.addObject("post", createdPost);
			mv.addObject("listing",saleListing);
			mv.setViewName("singleSale");
			break;

		case "Reply to Swapper":
			SwapListing swapListing = swapListingDAO.findById(listingId);
			createdPost = postDAO.addToSwap(post, user, swapListing);
			mv.addObject("listing",swapListing);
			mv.addObject("post", createdPost);
			mv.setViewName("singleSwap");
			break;
		default:
			break;
		}

		return mv;
	}
}
