package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SwapListing;
import com.skilldistillery.sportswap.entities.User;

@Transactional
@Service
public class SwapListingDAOImpl implements SwapListingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public SwapListing findById(int id) {

		return em.find(SwapListing.class, id);
	}

	@Override
	public List<SwapListing> getAllSwapListings() {

		List<SwapListing> swaps = null;
		String query = "SELECT s FROM SwapListing s";
		swaps = em.createQuery(query, SwapListing.class).getResultList();
		return swaps;
	}

	@Override
	public SwapListing add(SwapListing listing, List<Item> items, Address address, User user) {

		//get managed objects
		User managedUser = em.find(User.class, user.getId());
		Address managedAddress = em.find(Address.class, address.getId());
		
		for (Item item : items) {
			
			Item itemToAdd = em.find(Item.class, item.getId());
			if (itemToAdd != null) {
				listing.addItem(itemToAdd);
				itemToAdd.addSwapListing(listing);
			}
		}
		
		listing.setSwapAddress(managedAddress);
		listing.setSwappingUser(managedUser);
		listing.setActive(true);
		
		em.persist(listing);
		em.flush();
		return listing;

	}

	@Override
	public SwapListing update(SwapListing listing, int id) {
		SwapListing updatedListing = em.find(SwapListing.class, id);

		updatedListing.setActive(listing.isActive());
		updatedListing.setCreated(listing.getCreated());
		updatedListing.setUpdated(listing.getUpdated());
		updatedListing.setDeactivated(listing.getDeactivated());

		return updatedListing;

	}
	@Override
	public boolean deactivate(int id) {
		boolean deactivated = false;
		
		SwapListing swap = em.find(SwapListing.class, id);
		if (em.contains(swap)) {
			swap.setActive(false);
			deactivated = true;
		}
		
		return deactivated;
	}
}
