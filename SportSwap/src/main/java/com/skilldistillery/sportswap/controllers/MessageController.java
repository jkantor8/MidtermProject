package com.skilldistillery.sportswap.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.sportswap.data.MessageDAO;
import com.skilldistillery.sportswap.data.UserDAO;
import com.skilldistillery.sportswap.entities.Message;
import com.skilldistillery.sportswap.entities.User;

@Controller
public class MessageController {

	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(path = "/messages", method = RequestMethod.GET)
	public String getMessagesForUser(Model model, HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			String username = user.getUsername();
			model.addAttribute("messages", messageDAO.findByReceiver(username));
		} else {
			// Handle the case when the username is not found in the session
			return "home";
		}
		return "messages";
	}

	@RequestMapping(path = "/new-message", method = RequestMethod.GET)
	public String newMessageForm(Model model) {
	    model.addAttribute("message", new Message());
	    return "new-message";
	}

	@RequestMapping(path = "/new-message", method = RequestMethod.POST)
	public String createMessage(@ModelAttribute("message") Message message,
            @RequestParam("receiverUsername") String receiverUsername,
            HttpSession session) {
		
		User sender = (User) session.getAttribute("loggedInUser");
		User receiver = userDAO.findByUsername(receiverUsername);
		if (receiver != null) {
			if (sender != null) {
				message.setSender(sender);
				message.setReceiver(receiver);
				message.setCreated(LocalDateTime.now());
				messageDAO.save(message);
				return "redirect:/messages";
			} else {
				// Handle the case when the username is not found in the session
				return "error";
			}
		} else {
			return "error";
		}
	}
}
