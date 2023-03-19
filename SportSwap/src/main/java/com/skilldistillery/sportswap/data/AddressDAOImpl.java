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
	
	@Override
	@Transactional
	public Address updateAddress(int id, Address updatedAddress) {
		Address address = em.find(Address.class, id);
		address.setStreet(updatedAddress.getStreet());
		address.setStreet2(updatedAddress.getStreet2());
		address.setCity(updatedAddress.getCity());
		address.setState(updatedAddress.getState());
		address.setPostalCode(updatedAddress.getPostalCode());
		address.setCountryCode(updatedAddress.getCountryCode());
		return address;
	}
}
