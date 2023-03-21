package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Item;

public interface ItemDAO {
	
	Item findItemById(int itemId);
	
	List<Item> findAll();
	
	List<Item> findItemByKeyword(String itemName);

	Item add(Item item, int ageGroupId, int sportId, int conditionId, int userId);
	
	Item update(int id, Item item);
	
	List<Item> findItemsByUser(int id);
}
