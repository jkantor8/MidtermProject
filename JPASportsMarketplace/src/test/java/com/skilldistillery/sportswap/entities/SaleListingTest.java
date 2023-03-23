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

class SaleListingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private SaleListing saleListing;
	
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
		saleListing = em.find(SaleListing.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_entity_mapping() {
		assertNotNull(saleListing);
		assertEquals(189.00,saleListing.getPrice());
	}
	
	@Test
	void test_user_ManyToOne_mapping() {
		assertNotNull(saleListing);
		assertNotNull(saleListing.getSellingUser());
		assertEquals(2, saleListing.getSellingUser().getId());
	}
	
	@Test
	void test_SaleListing_to_Item_OneToOne() {
		assertNotNull(saleListing);
		assertNotNull(saleListing.getItem());
		assertEquals("Golf Clubs",saleListing.getItem().getName());
		assertEquals("Titleist",saleListing.getItem().getBrand());
		assertEquals("https://xanimal37.github.io/img/golfclubs.jpg",saleListing.getItem().getImageUrl());
	}
	
	@Test
	void test_SaleListing_Post_OneToMany_mapping() {
		assertNotNull(saleListing);
		assertNotNull(saleListing.getSaleListingPosts());
		assertTrue(saleListing.getSaleListingPosts().size() > 0);
	}

}
