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
		assertEquals("Golf Clubs", item.getName());
		assertEquals("These clubs are practically new. Adult sized set with nice bag. Golf cart not included.", item.getDescription());
		assertEquals("https://xanimal37.github.io/img/golfclubs.jpg", item.getImageUrl());
		assertEquals("Titleist", item.getBrand());
		assertNotNull(item.getGender());
		assertTrue(item.isActive());
		assertNull(item.getCreated());
		assertNull(item.getUpdated());
		assertNull(item.getDeactivated());
		
	
	}
	@Test
	void test_Item_to_SaleListing_OneToOne() {
		assertNotNull(item);
		assertNotNull(item.getSaleListing());
		assertEquals(189.00, item.getSaleListing().getPrice());
		assertTrue(item.getSaleListing().isActive());
	}
	
	@Test
	void test_Item_to_User_ManyToOne() {
		assertNotNull(item);
		assertNotNull(item.getUserItem());
		assertEquals("Bobafet1" ,item.getUserItem().getUsername());
		assertEquals("bobafet1@gmail.com", item.getUserItem().getEmail());
	}
	
	@Test
	void test_Item_to_AgeGroup_ManyToOne() {
		assertNotNull(item);
		assertNotNull(item.getAgeGroup());
		assertEquals("ADULT" ,item.getAgeGroup().getAge());
	}
	
	@Test
	void test_Item_to_Sport_ManyToOne() {
		assertNotNull(item);
		assertNotNull(item.getSportItem());
		assertEquals("Golf", item.getSportItem().getName());
	}
	
	@Test
	void test_Item_to_Condition_ManyToOne() {
		assertNotNull(item);
		assertNotNull(item.getItemCondition());
		assertNotNull("New", item.getItemCondition().getName());
	}
	
	@Test
	void test_item_to_SwapListing_ManyToMany() {
		assertNotNull(item);
		assertNotNull(item.getSwapListings());
		assertNotNull(item.getSwapListings().isEmpty());
	}

}
