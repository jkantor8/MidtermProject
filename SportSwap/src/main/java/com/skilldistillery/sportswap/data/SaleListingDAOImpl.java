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
import com.skilldistillery.sportswap.entities.SaleListing;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.User;

@Transactional
@Service
public class SaleListingDAOImpl implements SaleListingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public SaleListing findById(int id) {
		return em.find(SaleListing.class, id);
	}

	@Override
	public List<SaleListing> getAllSaleListings() {
		List<SaleListing> sales = null;
		String query = "SELECT s FROM SaleListing s";
		sales = em.createQuery(query, SaleListing.class).getResultList();
		return sales;
	}

	@Override
	public SaleListing add(SaleListing listing, Item item, User user) {

		Item managedItem = em.find(Item.class, item.getId());
		User managedUser = em.find(User.class, user.getId());

		listing.setItem(managedItem);
		listing.setSellingUser(managedUser);
		listing.setActive(true);

		em.persist(listing);
		em.flush();
		return listing;
	}

	@Override
	public SaleListing update(SaleListing listing, int id) {
		SaleListing updatedListing = em.find(SaleListing.class, id);

		updatedListing.setPrice(listing.getPrice());
		updatedListing.setActive(listing.isActive());
		updatedListing.setCreated(listing.getCreated());
		updatedListing.setUpdated(listing.getUpdated());
		updatedListing.setDeactivated(listing.getDeactivated());

		return updatedListing;
	}

	@Override
	public List<SaleListing> findSaleListingsByUser(int sellingUser) {
		String jpql = "SELECT s FROM SaleListing s WHERE s.sellingUser.id = :sellingUser";
		List<SaleListing> userSaleListings = em.createQuery(jpql, SaleListing.class)
				.setParameter("sellingUser", sellingUser).getResultList();
		return userSaleListings;
	}

	@Override
	public SaleListing getRandom() {
		SaleListing listing = null;
		Random rand = new Random();
		List<SaleListing> listings = getAllSaleListings();
		if (listings != null && listings.size()>0) {
			listing = listings.get(rand.nextInt(listings.size()));
		}
		return listing;
	}

	// looks for a listing matching sport1
	// if not found uses sport two
	// otherwise will be null
	public SaleListing getLatestBySport(Sport sport1, Sport sport2) {
		SaleListing listing = null;
		List<SaleListing> listings = getAllSaleListings();
		for (SaleListing s : listings) {
			if (s.getItem().getSportItem().equals(sport1)) {
				listing = s;
				break;
			}
		}
		if (listing == null) {
			for (SaleListing s : listings) {
				if (s.getItem().getSportItem().equals(sport2)) {
					listing = s;
					break;
				}
			}
		}
		return listing;
	}

	@Override
	public boolean deactivate(int id) {
		boolean deactivated = false;

		SaleListing sale = em.find(SaleListing.class, id);
		if (em.contains(sale)) {
			sale.setActive(false);
			deactivated = true;
		}

		return deactivated;
	}

}
