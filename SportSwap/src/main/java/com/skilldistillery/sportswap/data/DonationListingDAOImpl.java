package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.DonationListing;

@Transactional
@Service
public class DonationListingDAOImpl implements DonationListingDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<DonationListing> getAllDonationListings(){
		List<DonationListing> donations = null;
		String query = "SELECT d FROM DonationListing d";
		donations = em.createQuery(query, DonationListing.class).getResultList();
		return donations;
		
	}
}
