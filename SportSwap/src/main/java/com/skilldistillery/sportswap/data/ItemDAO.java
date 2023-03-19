package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Item;

public interface ItemDAO {
	
	List<Item> findAll();
	
	List<Item> findItemByKeyword(String itemName);

	Item add(Item item);
	
	Item update(int id, Item item);
}
