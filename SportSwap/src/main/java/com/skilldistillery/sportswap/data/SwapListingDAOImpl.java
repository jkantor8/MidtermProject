package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.SwapListing;

@Transactional
@Service
public class SwapListingDAOImpl implements SwapListingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SwapListing> getAllSwapListings() {

		List<SwapListing> swaps = null;
		String query = "SELECT s FROM SwapListing s";
		swaps = em.createQuery(query, SwapListing.class).getResultList();
		return swaps;
	}
	
	@Override
	@Transactional
	public SwapListing add(SwapListing swapListing) {
		em.persist(swapListing);
		return swapListing;
		
	}

}
