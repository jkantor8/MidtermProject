package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;

public interface DonationListingDAO {
	
	DonationListing findById(int id);

	List<DonationListing> getAllDonationListings();
	
	DonationListing add(DonationListing listing, List<Item> donationItems, int addressId);
	
	DonationListing update(DonationListing listing, int id);
	
	
	boolean deactivate(int id);
}
