package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.DonationListing;

public interface DonationListingDAO {
	
	DonationListing findById(int id);

	List<DonationListing> getAllDonationListings();
	
	DonationListing add(DonationListing listing, int addressId);
}
