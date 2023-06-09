package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DonationListingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;

	private DonationListing donationListing;

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
		donationListing = em.find(DonationListing.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_entity_mapping() {
		assertNotNull(donationListing);
		assertTrue(donationListing.isActive());
		assertNull(donationListing.getEventStart());
		assertNull(donationListing.getEventEnd());

	}

	@Test
	void test_DonationListing_Address_OneToOne_mapping() {
		assertNotNull(donationListing);
		assertNotNull(donationListing.getDonationAddress());
		assertEquals(9, donationListing.getDonationAddress().getId());
		assertEquals("345 WackaWacka Ave.", donationListing.getDonationAddress().getStreet());
		assertEquals("Miami", donationListing.getDonationAddress().getCity());
		assertEquals("78678", donationListing.getDonationAddress().getPostalCode());
	}

	@Test
	void test_DonationListing_User_ManyToOne_mapping() {
		assertNotNull(donationListing);
		assertNotNull(donationListing.getUser());
		assertEquals("bobjohnson", donationListing.getUser().getUsername());

	}

	@Test
	void test_DonationListing_Post_OneToMany_mapping() {
		assertNotNull(donationListing);
		assertNotNull(donationListing.getDonationListingPosts());
		assertTrue(donationListing.getDonationListingPosts().size() > 0);
	}
	@Test
	void test_Sport_DonationListing_ManyToMany() {
		assertNotNull(donationListing);
		assertNotNull(donationListing.getSports());
		assertFalse(donationListing.getSports().isEmpty());
	}
}
