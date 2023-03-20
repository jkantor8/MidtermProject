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
@Table(name= "age_group")
public class AgeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String age;
	
	@OneToMany(mappedBy="ageGroup")
	private List<Item> items;
	
	public AgeGroup() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		if (!items.contains(item)) {
			items.add(item);
			if(item.getAgeGroup() != null) {
				item.getAgeGroup().removeItem(item);
			}
			item.setAgeGroup(this);
		}
	}

	public void removeItem(Item item) {
		if (items != null && items.contains(item)) {
			items.remove(item);
			item.setAgeGroup(null);
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
		AgeGroup other = (AgeGroup) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "AgeGroup [id=" + id + ", age=" + age + "]";
	}
	
}
