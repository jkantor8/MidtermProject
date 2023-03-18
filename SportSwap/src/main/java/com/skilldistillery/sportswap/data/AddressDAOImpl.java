package com.skilldistillery.sportswap.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Address;

@Transactional
@Service
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Address add(Address address) {
		em.persist(address);
		return address;
	}
}
