package com.skilldistillery.sportswap.data;

import com.skilldistillery.sportswap.entities.User;

public interface UserDAO {
	
	User login(String name, String pw);
	
	User add(User user);
}
