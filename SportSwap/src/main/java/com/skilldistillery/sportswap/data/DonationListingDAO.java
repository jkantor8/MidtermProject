package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.User;

public interface DonationListingDAO {
	
	DonationListing findById(int id);

	List<DonationListing> getAllDonationListings();
	
	DonationListing add(DonationListing listing, List<Item> items, Address address, User user);
	
	DonationListing update(DonationListing listing, int id);

	List<DonationListing> findDonationListingsByUser(int user);
	
	DonationListing getRandom();
	
	boolean deactivate(int id);

}
