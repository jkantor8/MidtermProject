package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;

@Transactional
@Service
public class SaleListingDAOImpl implements SaleListingDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SaleListing findById(int id) {
		return em.find(SaleListing.class, id);
	}
	
	@Override
	public List<SaleListing> getAllSaleListings(){
		List<SaleListing> sales = null;
		String query = "SELECT s FROM SaleListing s";
		sales = em.createQuery(query, SaleListing.class).getResultList();
		return sales;
	}
	
	@Override
	public SaleListing add(SaleListing listing, int itemId) {
		
		Item item =em.find(Item.class, itemId);
		
		listing.setItem(item);
		
		return listing;
	}

	@Override
	public SaleListing update(SaleListing listing, int id) {
		SaleListing updatedListing =em.find(SaleListing.class, id);
		
		updatedListing.setPrice(listing.getPrice());
		updatedListing.setActive(listing.isActive());
		updatedListing.setCreated(listing.getCreated());
		updatedListing.setUpdated(listing.getUpdated());
		updatedListing.setDeactivated(listing.getDeactivated());
		
		
		return updatedListing;
	}
	
	
}
