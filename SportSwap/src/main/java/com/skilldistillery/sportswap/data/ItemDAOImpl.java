package com.skilldistillery.sportswap.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.sportswap.entities.Item;

@Service
@Transactional
public class ItemDAOImpl implements ItemDAO {
	
	@PersistenceContext
	private EntityManager em;

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
	public Item add(Item item) {
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

}