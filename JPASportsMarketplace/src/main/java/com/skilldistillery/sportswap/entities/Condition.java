package com.skilldistillery.sportswap.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_condition")
public class Condition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "itemCondition")
	private List<Item> items;

	public Condition() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		if (items == null) {
			items = new ArrayList<>();
		}
		if (!items.contains(item)) {

			items.add(item);
			if (item.getItemCondition() != null) {
				item.getItemCondition().removeItem(item);
			}
			item.setItemCondition(this);
		}
	}

	public void removeItem(Item item) {
		if (items != null && items.contains(item)) {
			items.remove(item);
			item.setItemCondition(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condition other = (Condition) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return  name;
	}

}
