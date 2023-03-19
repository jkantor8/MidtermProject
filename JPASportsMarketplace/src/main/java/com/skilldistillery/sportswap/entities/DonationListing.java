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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "donation_listing")
public class DonationListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
	
	@CreationTimestamp
	private LocalDateTime created;
	
	@UpdateTimestamp
	private LocalDateTime updated;
	
	private LocalDateTime deactivated;
	
	@Column(name= "event_start")
	private LocalDateTime eventStart;
	
	@Column(name= "event_end")
	private LocalDateTime eventEnd;
	
	@OneToOne
	@JoinColumn (name= "address_id")
	private Address donationAddress;
	
	@ManyToOne 
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="donationListing")
	private List<Post> donationListingPosts;
	
	@ManyToMany(mappedBy="sportDonationListings")
	private List<Sport> sports;
	
	@ManyToMany(mappedBy="donationListingItems")
	private List<Item> items;
	
	public DonationListing() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	

	public LocalDateTime getEventStart() {
		return eventStart;
	}

	public void setEventStart(LocalDateTime eventStart) {
		this.eventStart = eventStart;
	}

	public LocalDateTime getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(LocalDateTime eventEnd) {
		this.eventEnd = eventEnd;
	}

	public Address getDonationAddress() {
		return donationAddress;
	}

	public void setDonationAddress(Address donationAddress) {
		this.donationAddress = donationAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getDonationListingPosts() {
		return donationListingPosts;
	}

	public void setDonationListingPosts(List<Post> donationListingPosts) {
		this.donationListingPosts = donationListingPosts;
	}
	
	public void addDonationListingPost(Post donationPost) {
		if (donationListingPosts == null) {
			donationListingPosts = new ArrayList<>();
		}
		if (!donationListingPosts.contains(donationPost)) {

			donationListingPosts.add(donationPost);
			if (donationPost.getDonationListing() != null) {
				donationPost.getDonationListing().removeDonationListingPost(donationPost);
			}
			donationPost.setDonationListing(this);
		}
	}

	public void removeDonationListingPost(Post donationPost) {
		if (donationListingPosts != null && donationListingPosts.contains(donationPost)) {
			donationListingPosts.remove(donationPost);
			donationPost.setDonationListing(null);
		}
	}
	
	

	public List<Sport> getSports() {
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}
	
	public void addSport(Sport sport) {
		if(sports ==null) {
			sports = new ArrayList<>();
			}
			if(!sports.contains(sport)) {
				sports.add(sport);
				sport.addDonationListing(this);
				}
			}
	
	public void removeSport(Sport sport) {
		if(sports != null && sports.contains(sport)) {
			sports.remove(sport);
			sport.removeDonationListing(this);
		}
	}
	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		if(items ==null) {
			items = new ArrayList<>();
			}
			if(!items.contains(item)) {
				items.add(item);
				item.addDonationListingItem(this);
				}
			}
	
	public void removeItem(Item item) {
		if(items != null && items.contains(item)) {
			items.remove(item);
			item.removeDonationListingItem(this);
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
		DonationListing other = (DonationListing) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "DonationListing [id=" + id + ", active=" + active + ", created=" + created + ", updated=" + updated
				+ ", deactivated=" + deactivated + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + "]";
	}
	
	
}
