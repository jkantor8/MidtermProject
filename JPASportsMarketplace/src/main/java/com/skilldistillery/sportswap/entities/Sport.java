package com.skilldistillery.sportswap.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Sport {

	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	

	private String name;
	
	@OneToOne(mappedBy="sportItem")
	private Item item;

	@ManyToMany
	@JoinTable(name="swap_listing_has_sport",
	joinColumns=@JoinColumn(name="sport_id"),
	inverseJoinColumns=@JoinColumn(name="swap_listing_id"))
	private List<SwapListing> sportSwapListings;
	
	@ManyToMany
	@JoinTable(name="donation_listing_has_sport",
	joinColumns=@JoinColumn(name="sport_id"),
	inverseJoinColumns=@JoinColumn(name="donation_listing_id"))
	private List<DonationListing> sportDonationListings;
	
	@ManyToMany
	@JoinTable(name="user_has_favorite_sport",
	joinColumns=@JoinColumn(name="sport_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> userSports;


	public Sport() {
		super();
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
	
	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}
	



	public List<SwapListing> getSportSwapListings() {
		return sportSwapListings;
	}



	public void setSportSwapListings(List<SwapListing> sportSwapListings) {
		this.sportSwapListings = sportSwapListings;
	}
	
//	public void addSwapListings(SwapListing swapListing) {
//		if(sportSwapListings ==null) {
//		sportSwapListings = new ArrayList<>();
//		}
//		if(!sportSwapListings.contains(swapListing)) {
//			sportSwapListings.add(swapListing);
//			swapListing
//		}
//	}

	

	public List<DonationListing> getSportDonationListings() {
		return sportDonationListings;
	}



	public void setSportDonationListings(List<DonationListing> sportDonationListings) {
		this.sportDonationListings = sportDonationListings;
	}

	


	public List<User> getUserSports() {
		return userSports;
	}



	public void setUserSports(List<User> userSports) {
		this.userSports = userSports;
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
		Sport other = (Sport) obj;
		return id == other.id;
	}



	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + "]";
	}
	
}
