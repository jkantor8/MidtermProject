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

class ItemTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Item item;

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
		item = em.find(Item.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		item = null;
	}

	@Test
	void test_item_entity_mapping() {
		assertNotNull(item);
		assertEquals("Punching Bag", item.getName());
		assertEquals("A punching bag that hangs from the ceiling. Attachment not included.", item.getDescription());
		assertEquals("https://xanimal37.github.io/toc/img/ICDC_toc_02.jpg", item.getImageUrl());
		assertEquals("TKO", item.getBrand());
		assertNull(item.getGender());
		assertTrue(item.isActive());
		assertNull(item.getCreated());
		assertNull(item.getUpdated());
		assertNull(item.getDeactivated());
		
	
	}
	@Test
	void test_Item_to_SaleListing_OneToOne() {
		assertNotNull(item);
		assertNotNull(item.getSaleListing());
		assertEquals(20.00, item.getSaleListing().getPrice());
		assertTrue(item.getSaleListing().isActive());
	}

}
