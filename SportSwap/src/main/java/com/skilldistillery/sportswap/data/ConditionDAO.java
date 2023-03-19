package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Condition;

public interface ConditionDAO {
	Condition findById(int id);

	List<Condition> findAll();

	Condition create(Condition condition);

	Condition update(int id, Condition condition);

	boolean deleteById(int id);
}
