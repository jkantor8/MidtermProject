package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Message;

public interface MessageDAO {

	List<Message> findAll();
	
	List<Message> findMessageByKeyword(String message);
	
	Message add(Message message);
	
}
