package com.skilldistillery.sportswap.data;

import com.skilldistillery.sportswap.entities.Post;

public interface PostDAO {
	
	Post findById(int id);

	Post add (Post post);
	
	Post update (int id, Post post);
	
	boolean deactivate(int id);
}
