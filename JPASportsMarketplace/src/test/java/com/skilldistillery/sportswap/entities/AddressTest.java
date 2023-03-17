package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPASportsMarketplace");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}
	
	@Test
	void test_address_entity_mapping() {
		assertNotNull(address);
		assertEquals("2929 Beach St",address.getStreet());
		assertNull(address.getStreet2());
		assertEquals("Mendota Heights",address.getCity());
		assertEquals("55555",address.getPostalCode());
	}
	
	@Test
	  void test_Address_DonationListing_OneToOne_mapping() {
	     assertNotNull(address);
	     assertNotNull(address.getDonationListing());
	     assertTrue(address.getDonationListing().isActive());
	    
	  }

}

