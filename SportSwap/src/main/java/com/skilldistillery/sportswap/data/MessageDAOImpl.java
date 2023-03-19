package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Message;

@Service
@Transactional
public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Message> findAll() {
		String query = "SELECT message FROM Message message";
		return em.createQuery(query, Message.class).getResultList();
	}

	@Override
	public List<Message> findMessageBySubject(String subject) {
		String query = "SELECT message FROM message WHERE message.subject"
				+ " LIKE :subject";
		List<Message> messageList = em.createQuery(query, Message.class)
				.setParameter("subject", "%" + subject + "%")
				.getResultList();
		return messageList;
	}

	@Override
	public Message add(Message message) {
			em.persist(message);
			em.flush();
		return message;
	}
}
