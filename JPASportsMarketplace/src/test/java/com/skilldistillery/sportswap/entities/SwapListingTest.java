package com.skilldistillery.sportswap.entities;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

class SwapListingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private SwapListing swapListing;
	
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
		swapListing = em.find(SwapListing.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_entity_mapping() {
		assertNotNull(swapListing);
		assertTrue(swapListing.isActive());
	}
	
	@Test
	  void test_SwapListing_Address_OneToOne_mapping() {
	     assertNotNull(swapListing);
	     assertNotNull(swapListing.getSwapAddress());
	     assertEquals(6, swapListing.getSwapAddress().getId());
	     assertEquals("1200 W. 78th St.", swapListing.getSwapAddress().getStreet());
	     assertEquals("Denver", swapListing.getSwapAddress().getCity());
	     assertEquals("78787", swapListing.getSwapAddress().getPostalCode());
	  }
	
	@Test
	void test_SwapListing_User_ManyToOne() {
		assertNotNull(swapListing);
		assertNotNull(swapListing.getSwappingUser());
		assertEquals("Bobafet1",swapListing.getSwappingUser().getUsername());
		assertEquals("starwars",swapListing.getSwappingUser().getPassword());
		assertEquals("bobafet1@gmail.com",swapListing.getSwappingUser().getEmail());
		
	}
	
	@Test
	void test_SwapListing_Post_OneToMany_mapping() {
	assertNotNull(swapListing);
	assertNotNull(swapListing.getSwapListingPosts());
	
	}
	
	@Test
	void test_SwapListing_Sport_ManyToMany() {
		assertNotNull(swapListing);
		assertNotNull(swapListing.getSports());
		assertFalse(swapListing.getSports().isEmpty());
	}
	@Test
	void test_SwapListing_item_ManyToMany() {
		assertNotNull(swapListing);
		assertNotNull(swapListing.getItems());
		assertFalse(swapListing.getItems().isEmpty());
	}
	
	

}
