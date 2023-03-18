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

class MessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	private Message message;
	
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
		message = em.find(Message.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Message_entity_mapping() {
		assertNotNull(message);
		assertEquals("Hi! What color is the punching bag?",message.getContent());
	}

	@Test
	void test_Message_User_ManyToOne_relationship_mapping() {
		assertNotNull(message);
		assertNotNull(message.getSender());
		assertNotNull(message.getReceiver());
		assertEquals(2, message.getSender().getId());
		assertEquals(3, message.getReceiver().getId());
		assertEquals("Hi! What color is the punching bag?", message.getContent());
	}
}
