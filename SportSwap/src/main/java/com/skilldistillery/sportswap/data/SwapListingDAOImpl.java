package com.skilldistillery.sportswap.data;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.Sport;
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

		// get managed objects
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

	@Override
	public SwapListing getRandom() {
		Random rand = new Random();
		List<SwapListing> listings = getAllSwapListings();
		SwapListing listing = listings.get(rand.nextInt(listings.size()));
		return listing;
	}

	// looks for a listing matching sport1
	// if not found uses sport two
	// otherwise will be null
	public SwapListing getLatestBySport(Sport sport1, Sport sport2) {
		SwapListing listing = null;
		List<SwapListing> listings = getAllSwapListings();
		for (SwapListing s : listings) {
			for (Item i : s.getItems()) {
				if (i.getSportItem().equals(sport1)) {
					listing = s;
					break;
				}
				if (listing != null) {
					break;
				}
			}
		}
		if (listing == null) {
			for (SwapListing s : listings) {
				for (Item i : s.getItems()) {
					if (i.getSportItem().equals(sport1)) {
						listing = s;
						break;
					}
					if (listing != null) {
						break;
					}
				}
			}
		}
		return listing;
	}

	@Override
	public List<SwapListing> findSwapListingsByUser(int swappingUser) {
		String jpql = "SELECT s FROM SwapListing s WHERE s.swappingUser.id = :swappingUser";
		List<SwapListing> userSwapListings = em.createQuery(jpql, SwapListing.class)
				.setParameter("swappingUser", swappingUser).getResultList();
		return userSwapListings;
	}

}
