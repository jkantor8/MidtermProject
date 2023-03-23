package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SportTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Sport sport;

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
		sport = em.find(Sport.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		sport = null;
	}

	@Test
	void test_sport_entity_mapping() {
		assertNotNull(sport);
		assertEquals("Hockey", sport.getName());
	
	}
	
	@Test
	void test_sport_to_item_OneToMany() {
		assertNotNull(sport);
		assertNotNull(sport.getItems());
		assertFalse(sport.getItems().isEmpty());
		
	}
	
	@Test
	void test_Sport_SwapListing_ManyToMany() {
		assertNotNull(sport);
		assertNotNull(sport.getSportSwapListings());
		assertFalse(sport.getSportSwapListings().isEmpty());
	}

	
	@Test
	void test_Sport_DonationListing_ManyToMany() {
		assertNotNull(sport);
		assertNotNull(sport.getSportDonationListings());
		assertFalse(sport.getSportDonationListings().isEmpty());
	}
	@Test
	void test_Sport_User_ManyToMany() {
		assertNotNull(sport);
		assertNotNull(sport.getUserSports());
		assertFalse(sport.getUserSports().isEmpty());
	}
}
