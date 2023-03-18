package com.skilldistillery.sportswap.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "age_group")
public class AgeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String age;
	
	@OneToOne(mappedBy="ageGroup")
	private Item itemAgeGroup;
	
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

	public Item getItemAgeGroup() {
		return itemAgeGroup;
	}

	public void setItemAgeGroup(Item itemAgeGroup) {
		this.itemAgeGroup = itemAgeGroup;
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
