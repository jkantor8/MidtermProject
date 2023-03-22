package com.skilldistillery.sportswap.data;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.DonationListing;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.User;

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
	public List<DonationListing> getAllDonationListings() {
		List<DonationListing> donations = null;
		String query = "SELECT d FROM DonationListing d";
		donations = em.createQuery(query, DonationListing.class).getResultList();
		return donations;

	}

	@Override
	public DonationListing add(DonationListing listing, List<Item> items, Address address, User user) {

		// get managed objects
		User managedUser = em.find(User.class, user.getId());
		Address managedAddress = em.find(Address.class, address.getId());

		for (Item item : items) {

			Item itemToAdd = em.find(Item.class, item.getId());
			if (itemToAdd != null) {
				listing.addItem(itemToAdd);
				itemToAdd.addDonationListingItem(listing);
			}
		}

		listing.setDonationAddress(managedAddress);
		listing.setUser(managedUser);
		listing.setActive(true);

		em.persist(listing);
		em.flush();
		return listing;
	}

	@Override
	public DonationListing update(DonationListing listing, int id) {
		DonationListing updatedListing = em.find(DonationListing.class, id);

		updatedListing.setActive(listing.isActive());
		updatedListing.setCreated(listing.getCreated());
		updatedListing.setUpdated(listing.getUpdated());
		updatedListing.setDeactivated(listing.getDeactivated());
		updatedListing.setEventStart(listing.getEventStart());
		updatedListing.setEventEnd(listing.getEventEnd());

		return updatedListing;
	}

	@Override
	public List<DonationListing> findDonationListingsByUser(int userId) {
		String jpql = "SELECT d FROM DonationListing d WHERE d.user.id = :user";
		List<DonationListing> userDonationListings = em.createQuery(jpql, DonationListing.class)
				.setParameter("user", userId).getResultList();
		return userDonationListings;
	}
	
	@Override
	public DonationListing getRandom() {
		Random rand = new Random();
		List<DonationListing> listings= getAllDonationListings();
		DonationListing listing = listings.get(rand.nextInt(listings.size()));
		return listing;
	}
	

	@Override
	public boolean deactivate(int id) {
		boolean deactivated = false;

		DonationListing donation = em.find(DonationListing.class, id);
		if (em.contains(donation)) {
			donation.setActive(false);
			deactivated = true;
		}

		return deactivated;
	}
	
	

}
