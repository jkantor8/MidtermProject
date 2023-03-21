package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

public interface SwapListingDAO {
	
	SwapListing findById(int id);

	List<SwapListing> getAllSwapListings();
	
	SwapListing add(SwapListing listing, List<Item> items, Address address, User user);
	
	SwapListing update(SwapListing listing, int id);
	
	boolean deactivate(int id);
}
