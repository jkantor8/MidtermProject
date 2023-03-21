package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.SaleListing;

public interface SaleListingDAO {
	
	SaleListing findById(int id);
	
	List<SaleListing> getAllSaleListings();
	
	SaleListing add(SaleListing listing, int itemId);
	
	SaleListing update(SaleListing listing, int id);

	List<SaleListing> findSaleListingsByUser(int userId);
	
	boolean deactivate(int id);
	
}
