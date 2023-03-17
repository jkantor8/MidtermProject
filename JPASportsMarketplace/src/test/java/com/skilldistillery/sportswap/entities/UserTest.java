package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private User user;
	
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
		user = em.find(User.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("Bob",user.getUsername());
	}

	@Test
	void test_User_sentMessage_OneToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getSentMessages());
		assertTrue(user.getSentMessages().size() > 0);
	}
	@Test
	void test_User_receieMessage_OneToMany_mapping() {
		assertNotNull(user);
		assertNotNull(user.getReceivedMessages());
		assertTrue(user.getReceivedMessages().size() > 0);
	}
	
	@Test
	  void test_User_Address_OneToOne_mapping() {
		user = em.find(User.class, 1);
	     assertNotNull(user);
	     assertNotNull(user.getUserAddress());
	     assertEquals(1, user.getUserAddress().getId());
	     assertEquals("2929 Beach St", user.getUserAddress().getStreet());
	     assertEquals("Mendota Heights", user.getUserAddress().getCity());
	     assertEquals("55555", user.getUserAddress().getPostalCode());
	  }
	
	@Test
	void test_User_DonationListing_OneToMany_mapping() {
		user = em.find(User.class, 1);
		assertNotNull(user);
		assertNotNull(user.getDonationListings());
		assertTrue(user.getDonationListings().size() > 0);
	}
}
