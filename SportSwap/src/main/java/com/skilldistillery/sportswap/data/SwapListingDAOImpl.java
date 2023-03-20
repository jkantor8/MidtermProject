package com.skilldistillery.sportswap.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;
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
	public SwapListing add(SwapListing swapListing, List<Integer> itemIds, int addressId) {

		List<Item> items = new ArrayList<>();
		if (itemIds != null) {
			for (Integer id : itemIds) {
				Item itemToAdd = em.find(Item.class, id);
				if (itemToAdd != null) {
					items.add(itemToAdd);
					itemToAdd.addSwapListing(swapListing);
				}
			}
		}

		Address address = em.find(Address.class, addressId);
		swapListing.setItems(items);
		swapListing.setSwapAddress(address);

		em.persist(swapListing);
		em.flush();
		return swapListing;

	}
	
	@Override
	public SwapListing update(SwapListing listing, int id) {
		
		return null;
	}

}
