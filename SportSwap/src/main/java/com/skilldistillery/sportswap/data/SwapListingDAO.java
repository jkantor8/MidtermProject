package com.skilldistillery.sportswap.data;

import java.util.List;


import com.skilldistillery.sportswap.entities.SwapListing;

public interface SwapListingDAO {
	
	SwapListing findById(int id);

	List<SwapListing> getAllSwapListings();
	
	SwapListing add(SwapListing swapListing, List<Integer> itemIds, int addressId);
	
	SwapListing update(SwapListing listing, int id);
}
