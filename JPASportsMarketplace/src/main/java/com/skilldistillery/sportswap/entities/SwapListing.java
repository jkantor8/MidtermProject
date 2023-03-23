package com.skilldistillery.sportswap.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@Table(name = "swap_listing")
public class SwapListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
	
	private String title;
	
	private String description;
	
	@CreationTimestamp
	private LocalDateTime created;
	
	@UpdateTimestamp
	private LocalDateTime updated;
	
	private LocalDateTime deactivated;
	
	@ManyToOne
	@JoinColumn (name= "address_id")
	private Address swapAddress;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User swappingUser;
	
	@OneToMany(mappedBy= "swapListing")
	private List<Post> swapListingPosts;
	
	@ManyToMany(mappedBy="sportSwapListings")
	private List<Sport> sports;
	
	@ManyToMany(mappedBy="swapListings")
	private List<Item> items;
	
	
	
	public SwapListing() {
		
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

	public Address getSwapAddress() {
		return swapAddress;
	}

	public void setSwapAddress(Address swapAddress) {
		this.swapAddress = swapAddress;
	}
	

	public User getSwappingUser() {
		return swappingUser;
	}

	public void setSwappingUser(User swappingUser) {
		this.swappingUser = swappingUser;
	}

	public List<Post> getSwapListingPosts() {
		return swapListingPosts;
	}

	public void setSwapListingPosts(List<Post> swapListingPosts) {
		this.swapListingPosts = swapListingPosts;
	}

	public void addSwapListingPost(Post swapPost) {
		if (swapListingPosts == null) {
			swapListingPosts = new ArrayList<>();
		}
		if (!swapListingPosts.contains(swapPost)) {

			swapListingPosts.add(swapPost);
			if (swapPost.getSwapListing() != null) {
				swapPost.getSwapListing().removeSwapListingPost(swapPost);
			}
			swapPost.setSwapListing(this);
		}
	}

	public void removeSwapListingPost(Post swapPost) {
		if (swapListingPosts != null && swapListingPosts.contains(swapPost)) {
			swapListingPosts.remove(swapPost);
			swapPost.setSwapListing(null);
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
				sport.addSwapListing(this);
				}
			}
	
	public void removeSport(Sport sport) {
		if(sports != null && sports.contains(sport)) {
			sports.remove(sport);
			sport.removeSwapListing(this);
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
				item.addSwapListing(this);
				}
			}
	
	public void removeItem(Item item) {
		if(items != null && items.contains(item)) {
			items.remove(item);
			item.removeSwapListing(this);
		}
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		SwapListing other = (SwapListing) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "SwapListing [id=" + id + ", active=" + active + ", created=" + created + ", updated=" + updated
				+ ", deactivated=" + deactivated + "]";
	}
	
	
}
