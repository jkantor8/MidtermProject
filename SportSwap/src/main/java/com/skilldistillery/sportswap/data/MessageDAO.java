package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Message;

public interface MessageDAO {

	List<Message> findAll();

	List<Message> findMessageBySubject(String subject);

	Message save(Message message);

	List<Message> findByReceiver(String receiver);
	
	List<Message> findBySender(String senderUsername);

}
