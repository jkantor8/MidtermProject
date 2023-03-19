package com.skilldistillery.sportswap.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column (name="image_url")
	private String imageUrl;
	
	private String gender;
	
	private String brand;
	
	private boolean active;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;

	private LocalDateTime deactivated;
	
	@OneToOne(mappedBy="item")
	private SaleListing saleListing;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userItem;
	
	@OneToOne
	@JoinColumn(name= "age_group_id")
	private AgeGroup ageGroup;

	@OneToOne
	@JoinColumn(name= "sport_id")
	private Sport sportItem;
	
	@ManyToOne
	@JoinColumn(name="item_condition_id")
	private Condition itemCondition;
	
	@ManyToMany
	@JoinTable(name = "swap_listing_has_item", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "swap_listing_id"))
	private List<SwapListing> swapListingItems;
	
	@ManyToMany
	@JoinTable(name = "donation_listing_has_item", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "donation_listing_id"))
	private List<DonationListing> donationListingItems;
	
	
	
	public Item() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public LocalDateTime getDeactivated() {
		return deactivated;
	}

	public void setDeactivated(LocalDateTime deactivated) {
		this.deactivated = deactivated;
	}

	public SaleListing getSaleListing() {
		return saleListing;
	}

	public void setSaleListing(SaleListing saleListing) {
		this.saleListing = saleListing;
	}
	

	public User getUserItem() {
		return userItem;
	}

	public void setUserItem(User userItem) {
		this.userItem = userItem;
	}
	

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public Sport getSportItem() {
		return sportItem;
	}

	public void setSportItem(Sport sportItem) {
		this.sportItem = sportItem;
	}
	

	public Condition getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(Condition itemCondition) {
		this.itemCondition = itemCondition;
	}
	

	public List<SwapListing> getSwapListingItems() {
		return swapListingItems;
	}

	public void setSwapListingItems(List<SwapListing> swapListingItems) {
		this.swapListingItems = swapListingItems;
	}
	
	public void addSwapListingItem(SwapListing swapListing) {
		if(swapListingItems ==null) {
			swapListingItems = new ArrayList<>();
		}
		if(!swapListingItems.contains(swapListing)) {
			swapListingItems.add(swapListing);
			swapListing.addItem(this);
			}
		}

		public void removeSwapListingItem(SwapListing swapListing) {
			if(swapListingItems != null && swapListingItems.contains(swapListing)) {
				swapListingItems.remove(swapListing);
				swapListing.removeItem(this);
			}
		}
		
		

	public List<DonationListing> getDonationListingItems() {
			return donationListingItems;
		}

	public void setDonationListingItems(List<DonationListing> donationListingItems) {
			this.donationListingItems = donationListingItems;
		}
	
	
	public void addDonationListingItem(DonationListing donationListing) {
		if(donationListingItems ==null) {
			donationListingItems = new ArrayList<>();
		}
		if(!donationListingItems.contains(donationListing)) {
			donationListingItems.add(donationListing);
			donationListing.addItem(this);
			}
		}

		public void removeDonationListingItem(DonationListing donationListing) {
			if(donationListingItems != null && donationListingItems.contains(donationListing)) {
				donationListingItems.remove(donationListing);
				donationListing.removeItem(this);
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
		Item other = (Item) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", gender=" + gender + ", brand=" + brand + ", active=" + active + ", created=" + created
				+ ", updated=" + updated + ", deactivated=" + deactivated + "]";
	}
	
	
}