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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findMessageByKeyword(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message add(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
}
