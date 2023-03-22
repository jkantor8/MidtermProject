package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.User;

public interface SaleListingDAO {
	
	SaleListing findById(int id);
	
	List<SaleListing> getAllSaleListings();
	
	SaleListing add(SaleListing listing, Item item, User user);
	
	SaleListing update(SaleListing listing, int id);

	List<SaleListing> findSaleListingsByUser(int userId);
	
	SaleListing getRandom();
	
	SaleListing getLatestBySport(Sport sport1, Sport sport2);
	
	boolean deactivate(int id);
	
}
