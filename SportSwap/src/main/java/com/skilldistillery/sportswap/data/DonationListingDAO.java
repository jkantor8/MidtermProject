package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.DonationListing;

public interface DonationListingDAO {

	List<DonationListing> getAllDonationListings();
}
