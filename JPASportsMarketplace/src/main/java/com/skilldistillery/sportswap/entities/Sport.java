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
import javax.persistence.OneToMany;

@Entity
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "sportItem")
	private List<Item> items;

	@ManyToMany
	@JoinTable(name = "swap_listing_has_sport",
	joinColumns = @JoinColumn(name = "sport_id"), 
	inverseJoinColumns = @JoinColumn(name = "swap_listing_id"))
	private List<SwapListing> sportSwapListings;

	@ManyToMany
	@JoinTable(name = "donation_listing_has_sport", 
	joinColumns = @JoinColumn(name = "sport_id"), 
	inverseJoinColumns = @JoinColumn(name = "donation_listing_id"))
	private List<DonationListing> sportDonationListings;

	@ManyToMany
	@JoinTable(name = "user_has_favorite_sport", 
	joinColumns = @JoinColumn(name = "sport_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
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
			if(item.getSportItem() != null) {
				item.getSportItem().removeItem(item);
			}
			item.setSportItem(this);
		}
	}

	public void removeItem(Item item) {
		if (items != null && items.contains(item)) {
			items.remove(item);
			item.setSportItem(null);
		}
	}

	public List<SwapListing> getSportSwapListings() {
		return sportSwapListings;
	}

	public void setSportSwapListings(List<SwapListing> sportSwapListings) {
		this.sportSwapListings = sportSwapListings;
	}

	public void addSwapListing(SwapListing swapListing) {
	if(sportSwapListings ==null) {
	sportSwapListings = new ArrayList<>();
	}
	if(!sportSwapListings.contains(swapListing)) {
		sportSwapListings.add(swapListing);
		swapListing.addSport(this);
		}
	}

	public void removeSwapListing(SwapListing swapListing) {
		if(sportSwapListings != null && sportSwapListings.contains(swapListing)) {
			sportSwapListings.remove(swapListing);
			swapListing.removeSport(this);
		}
	}

	public List<DonationListing> getSportDonationListings() {
		return sportDonationListings;
	}

	public void setSportDonationListings(List<DonationListing> sportDonationListings) {
		this.sportDonationListings = sportDonationListings;
	}
	
	public void addDonationListing(DonationListing donationListing) {
	if(sportDonationListings ==null) {
		sportDonationListings = new ArrayList<>();
	}
	if(!sportDonationListings.contains(donationListing)) {
		sportDonationListings.add(donationListing);
		donationListing.addSport(this);
		}
	}

	public void removeDonationListing(DonationListing donationListing) {
		if(sportDonationListings != null && sportDonationListings.contains(donationListing)) {
			sportDonationListings.remove(donationListing);
			donationListing.removeSport(this);
		}
	}
	

	public List<User> getUserSports() {
		return userSports;
	}

	public void setUserSports(List<User> userSports) {
		this.userSports = userSports;
	}
	
	
	public void addUserFavoriteSport(User user) {
		if(userSports ==null) {
			userSports = new ArrayList<>();
		}
		if(!userSports.contains(user)) {
			userSports.add(user);
			user.addSport(this);
			}
		}

		public void removeUserFavoriteSport(User user) {
			if(userSports != null && userSports.contains(user)) {
				userSports.remove(user);
				user.removeSport(this);
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
		Sport other = (Sport) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + "]";
	}

}
