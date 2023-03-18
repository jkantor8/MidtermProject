package com.skilldistillery.sportswap.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Condition {
	


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String condition;

	public Condition() {
		super();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
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
		return "Condition [id=" + id + ", condition=" + condition + "]";
	}
	
	
}
