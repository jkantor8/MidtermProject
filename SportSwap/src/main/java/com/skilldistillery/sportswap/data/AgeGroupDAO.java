package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.AgeGroup;

public interface AgeGroupDAO {
	AgeGroup findById(int id);

	List<AgeGroup> findAll();

	AgeGroup create(AgeGroup ageGroup);

	AgeGroup update(int id, AgeGroup ageGroup);

	boolean deleteById(int id);
}

