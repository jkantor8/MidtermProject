package com.skilldistillery.sportswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		sport = em.find(Sport.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		sport = null;
	}

	@Test
	void test_sport_entity_mapping() {
		assertNotNull(sport);
		assertEquals("Boxing", sport.getName());
	
	}
	
	@Test
	void test_sport_to_item_OneToOne() {
		assertNotNull(sport);
		assertNotNull(sport.getItem());
		assertEquals("Punching Bag",sport.getItem().getName());
		assertEquals("TKO",sport.getItem().getBrand());
		assertEquals("https://xanimal37.github.io/toc/img/ICDC_toc_02.jpg",sport.getItem().getImageUrl());
		
	}

}
