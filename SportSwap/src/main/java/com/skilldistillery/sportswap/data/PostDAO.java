package com.skilldistillery.sportswap.data;

import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Post;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

public interface PostDAO {
	
	Post findById(int id);

	Post addToDonate(Post post, User user, DonationListing donationListing);
	Post addToSale(Post post, User user, SaleListing saleListing);
	Post addToSwap(Post post, User user, SwapListing swapListing);
	
	Post update (int id, Post post);
	
	boolean deactivate(int id);
}
