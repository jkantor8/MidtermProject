package com.skilldistillery.sportswap.data;

import java.util.List;

import com.skilldistillery.sportswap.entities.Sport;

public interface SportDAO {

	Sport findById(int id);

	List<Sport> findAll();

	Sport add(Sport sport);

	Sport update(int id, Sport sport);

	boolean deleteById(int id);
}
