package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.User;

public interface ItemDAO {
	
	Item findItemById(int itemId);
	
	List<Item> findAll();
	
	List<Item> findItemByKeyword(String itemName);

	Item add(Item item, int ageGroupId, int sportId, int conditionId, int userId);
	
	Item update(int id, Item item);
	
	List<Item> findItemsByUser(User user);
	
	List<Item> findItemsByIds(List<String> ids);
	
	List<Item> getRandomItems(int numItems);
	
	List<Item> getItemsBySports(List<Sport> sports);
}
