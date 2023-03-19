package com.skilldistillery.sportswap.data;

import com.skilldistillery.sportswap.entities.Address;

public interface AddressDAO {

	Address add(Address address);
	
	Address updateAddress(int id, Address address);
}
