package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SwapListing;

public interface SwapListingDAO {
	
	SwapListing findById(int id);

	List<SwapListing> getAllSwapListings();
	
	SwapListing add(SwapListing swapListing, List<Item> items, int addressId);
	
	SwapListing update(SwapListing listing, int id);
}
