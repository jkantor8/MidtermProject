package com.skilldistillery.sportswap.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.AgeGroup;
import com.skilldistillery.sportswap.entities.Condition;
import com.skilldistillery.sportswap.entities.Item;
import com.skilldistillery.sportswap.entities.Sport;
import com.skilldistillery.sportswap.entities.User;

@Service
@Transactional
public class ItemDAOImpl implements ItemDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Item findItemById(int itemId) {
		Item item = em.find(Item.class, itemId);
		return item;
	}

	@Override
	public List<Item> findAll() {
		String query = "SELECT item FROM Item item";
		return  em.createQuery(query, Item.class).getResultList();
	}

	@Override
	public List<Item> findItemByKeyword(String itemName) {
		String query = "SELECT item FROM Item item WHERE item.name LIKE :itemName";
		List <Item> itemList = em.createQuery(query, Item.class)
				.setParameter("itemName", "%" + itemName + "%")
				.getResultList();
		return itemList;
	}

	@Override
	public Item add(Item item, int ageGroupId, int sportId, int conditionId, int userId) {
		
		AgeGroup ageGroup = em.find(AgeGroup.class, ageGroupId);
		Sport sport = em.find(Sport.class, sportId);
		Condition condition  = em.find(Condition.class, conditionId);
		//associate user
		User user = em.find(User.class,userId);
		
		item.setAgeGroup(ageGroup);
		item.setItemCondition(condition);
		item.setSportItem(sport);
		item.setUserItem(user);
		
		item.setActive(true);
		
		//persists will return the item
		em.persist(item);
		em.flush();
		return item;
	}

	@Override
	public Item update(int id, Item item) {
		Item updatedItem = em.find(Item.class, id);
		updatedItem.setName(item.getName());
		updatedItem.setDescription(item.getDescription());
		updatedItem.setImageUrl(item.getImageUrl());
		updatedItem.setGender(item.getGender());
		updatedItem.setBrand(item.getBrand());
		updatedItem.setActive(item.isActive());
		updatedItem.setCreated(item.getCreated());
		updatedItem.setUpdated(item.getUpdated());
		updatedItem.setDeactivated(item.getDeactivated());
		
		return updatedItem;
	}
	
	@Override
	public List<Item> findItemsByUser(User user) {
		String query = "SELECT i FROM Item i WHERE i.userItem =:u AND i.active = 1";
		List<Item> items = em.createQuery(query, Item.class).setParameter("u",user).getResultList();
		return items;
	}
	
	@Override
	public List<Item> findItemsByIds(List<String> ids){
		List<Item> items = new ArrayList<>();
		for(String id : ids) {
			int id_number = Integer.parseInt(id);
			items.add(findItemById(id_number));
		}
		return items;
		
	}

}
