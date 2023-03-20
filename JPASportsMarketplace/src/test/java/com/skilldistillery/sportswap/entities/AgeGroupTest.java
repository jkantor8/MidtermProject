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

class AgeGroupTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private AgeGroup ageGroup;
	
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
		ageGroup = em.find(AgeGroup.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_entity_mapping() {
		assertNotNull(ageGroup);
		assertEquals("YOUTH",ageGroup.getAge());
	}
	@Test
	void test_AgeGroup_to_Item_OneToMany() {
		ageGroup = em.find(AgeGroup.class, 3);
		assertNotNull(ageGroup);
		assertNotNull(ageGroup.getItems());
		assertFalse(ageGroup.getItems().isEmpty());

	}

}
