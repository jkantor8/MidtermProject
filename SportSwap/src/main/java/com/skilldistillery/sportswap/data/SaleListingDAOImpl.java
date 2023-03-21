package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.SaleListing;
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
		List<SaleListing> userSaleListings = em.createQuery(jpql, SaleListing.class).setParameter("sellingUser", sellingUser)
				.getResultList();
		return userSaleListings;
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
