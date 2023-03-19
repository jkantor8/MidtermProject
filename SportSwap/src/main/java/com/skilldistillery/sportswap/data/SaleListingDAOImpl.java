package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.SaleListing;

@Transactional
@Service
public class SaleListingDAOImpl implements SaleListingDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<SaleListing> getAllSaleListings(){
		List<SaleListing> sales = null;
		String query = "SELECT s FROM SaleListing s";
		sales = em.createQuery(query, SaleListing.class).getResultList();
		return sales;
	}
}
