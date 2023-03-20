package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.SwapListing;

public interface SwapListingDAO {

	List<SwapListing> getAllSwapListings();
	
	SwapListing add(SwapListing swapListing, int addressId);
	
	
}
