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
import com.skilldistillery.sportswap.entities.SwapListing;

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
	public SwapListing add(SwapListing listing, List<Item> items, int addressId) {

		for (Item item : items) {
			Item itemToAdd = em.find(Item.class, item.getId());
			if (itemToAdd != null) {
				listing.addItem(itemToAdd);
				itemToAdd.addSwapListing(listing);
			}
		}

		Address address = em.find(Address.class, addressId);
		listing.setItems(items);
		listing.setSwapAddress(address);

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

}
