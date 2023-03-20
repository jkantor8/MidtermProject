package com.skilldistillery.sportswap.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;

@Transactional
@Service
public class DonationListingDAOImpl implements DonationListingDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DonationListing findById(int id) {
		return em.find(DonationListing.class, id);
	}
	
	@Override
	public List<DonationListing> getAllDonationListings(){
		List<DonationListing> donations = null;
		String query = "SELECT d FROM DonationListing d";
		donations = em.createQuery(query, DonationListing.class).getResultList();
		return donations;
		
	}
	
	@Override
	public DonationListing add(DonationListing listing, List<Integer> itemIds, int addressId) {
		
		List<Item> items = new ArrayList<>();
		if (itemIds != null) {
			for (Integer id : itemIds) {
				Item itemToAdd = em.find(Item.class, id);
				if (itemToAdd != null) {
					items.add(itemToAdd);
					itemToAdd.addDonationListingItem(listing);
				}
			}
		}
		
		
		Address address = em.find(Address.class, addressId);
		
		listing.setDonationAddress(address);
		em.persist(listing);
		em.flush();
		return listing;
		
	}
}
