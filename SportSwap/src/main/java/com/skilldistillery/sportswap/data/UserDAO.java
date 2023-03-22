package com.skilldistillery.sportswap.data;

import com.skilldistillery.sportswap.entities.Address;
import com.skilldistillery.sportswap.entities.User;

public interface UserDAO {
	
	User login(String name, String pw);
	
	User add(User user, Address address, int sportId, int sportId2);
	
	User findUserById(int id);
	
	User updateUser(int id, User user);

	User findByUsername(String receiverUsername);

	
}
