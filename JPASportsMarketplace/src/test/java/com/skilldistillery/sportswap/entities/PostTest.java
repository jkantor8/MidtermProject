package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;

	private Post post;

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
		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_entity_mapping() {
		assertNotNull(post);
		assertTrue(post.isActive());
		assertEquals("Hi this is really fun!", post.getComment());

	}

	@Test
	void test_Post_SwapListing_ManyToOne() {
		assertNotNull(post);
//		assertNotNull(post.getSwapListing());
//		assertNull(post.getSwapListing().getSwapListingPosts());
		assertNull(post.getSwapListing());

	}

	@Test
	void test_Post_DonationListing_ManyToOne() {
		assertNotNull(post);
		assertNotNull(post.getDonationListing().getDonationListingPosts());
		assertNull(post.getDonationListing().getEventStart());
		assertNull(post.getDonationListing().getEventEnd());

	}

	@Test
	void test_Post_SaleListing_ManyToOne() {
		post = em.find(Post.class, 2);
		assertNotNull(post);
		assertNotNull(post.getSaleListing().getSaleListingPosts());
		assertNull(post.getSaleListing().getCreated());
		assertEquals(20.0, post.getSaleListing().getPrice());

	}

}
