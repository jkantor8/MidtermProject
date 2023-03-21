package com.skilldistillery.sportswap.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.sportswap.data.AddressDAO;
import com.skilldistillery.sportswap.data.DonationListingDAO;
import com.skilldistillery.sportswap.data.SaleListingDAO;
import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private SaleListingDAO saleListingDAO;
	@Autowired
	private DonationListingDAO donationListingDAO;

	// directs to home page
	@RequestMapping(path = { "/", "home.do" })
	public String home(Model model, HttpSession session) {
		return "home";
	}

	// logging in
	@RequestMapping(path = "home.do", method = RequestMethod.POST, params = "login")
	public ModelAndView login(@RequestParam("username") String name, @RequestParam("password") String pw,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();

		User user = userDao.login(name, pw);

		if (user != null) {
			// Add user to session to remember who is logged in
			session.setAttribute("loggedInUser", user);
			session.setAttribute("userId", user.getId());
			session.setAttribute("username", user.getUsername());
			mv.addObject("user", user);
		} else {

		}
		return mv;
	}

	// logging out
	@RequestMapping(path = "home.do", method = RequestMethod.POST, params = "logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("loggedInUser") != null) {
			// remove user from session and reload
			session.setAttribute("loggedInUser", null);
		} else {

		}
		return mv;
	}

	// create an account
	// with redirect mapping to account view/edit page
	@RequestMapping(path = "createAccount.do", method = RequestMethod.POST)
	public ModelAndView createAccount(HttpSession session, @RequestParam("username") String name,
			@RequestParam("password") String pw, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("address2") String address2,
			@RequestParam("city") String city, @RequestParam("state_province") String state_province,
			@RequestParam("postalCode") String postalCode, @RequestParam("country") String country) {
		ModelAndView mv = new ModelAndView();
		// create command object
		// --> add user to database
		User newUser = new User();
		newUser.setActive(true);
		newUser.setEmail(email);
		newUser.setUsername(name);
		newUser.setPassword(pw);
		newUser.setRole("ACTIVE_USER");

		// use the addressDAO to cretae and add address to database
		// this will make sure it has an id that is not null
		// then assign to user
		Address add = new Address();
		add.setStreet(address);
		add.setStreet2(address2);
		add.setCity(city);
		add.setState(state_province);
		add.setPostalCode(postalCode);
		add.setCity(country);
		add.setCountryCode(country);

		add = addressDAO.add(add);

		newUser.setUserAddress(add);

		newUser = userDao.add(newUser);

		// add user to session so that user will be logged in
		session.setAttribute("loggedInUser", newUser);
		mv.setViewName("redirect:account.do");
		return mv;
	}

	// mapping to handle Redirect
	@RequestMapping(path = "account.do", method = RequestMethod.GET)
	public ModelAndView account_created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account");
		return mv;
	}

	// update the account
	@RequestMapping(path = "account.do", method = RequestMethod.POST)
	public ModelAndView account_update(HttpSession session, @RequestParam("username") String name,
			@RequestParam("password") String pw, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("address2") String address2,
			@RequestParam("city") String city, @RequestParam("state_province") String state_province,
			@RequestParam("postalCode") String postalCode, @RequestParam("country") String country) {
		// model
		ModelAndView mv = new ModelAndView();

		// get the user from the db
		// use the value of id of the session user
		int id = ((User) session.getAttribute("loggedInUser")).getId();

		// get user by id
		User updatedUser = userDao.findUserById(id);

		// update user info
		updatedUser.setEmail(email);
		updatedUser.setUsername(name);
		updatedUser.setPassword(pw);

		Address updatedAddress = updatedUser.getUserAddress();

		updatedAddress.setStreet(address);
		updatedAddress.setStreet2(address2);
		updatedAddress.setCity(city);
		updatedAddress.setPostalCode(postalCode);
		updatedAddress.setCity(country);
		updatedAddress.setCountryCode(country);

		// see if updating user will update address

		updatedUser.setUserAddress(updatedAddress);

		// send user to db
		User user = userDao.updateUser(id, updatedUser);
		// update session user
		session.setAttribute("loggedInUser", user);

		// Retrieve user sale listings
		List<SaleListing> userSaleListings = saleListingDAO.findSaleListingsByUser(id);
		System.out.println("User sale listings: " + userSaleListings);
		mv.addObject("userSaleListings", userSaleListings);

		if (user != null) {
			mv.addObject("result", "Account successfully updated!");
		} else {
			mv.addObject("result", "Sorry, there was a problem updating your account.");
		}

		mv.setViewName("account");
		return mv;
	}

	// for routing to the account creation page
	@RequestMapping(path = "createAccount.do", method = RequestMethod.GET)
	public ModelAndView createAccount() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_account");
		return mv;
	}

	// Retrieve user sale listings

	@RequestMapping(path = "viewUserSaleListings.do", method = RequestMethod.GET)
	public String viewUserSaleListings(HttpSession session, Model model) {
		int id = ((User) session.getAttribute("loggedInUser")).getId();

		List<SaleListing> userSaleListings = saleListingDAO.findSaleListingsByUser(id);

		model.addAttribute("userSaleListings", userSaleListings);

		return "viewUserSaleListings";
	}

	@RequestMapping(path = "viewUserDonationListings.do", method = RequestMethod.GET)
	public String viewUserDonationListings(HttpSession session, Model model) {
		int userId = ((User) session.getAttribute("loggedInUser")).getId();
		List<DonationListing> userDonationListings = donationListingDAO.findDonationListingsByUser(userId);
		model.addAttribute("userDonationListings", userDonationListings);
		return "viewUserDonationListings";
	}

}
