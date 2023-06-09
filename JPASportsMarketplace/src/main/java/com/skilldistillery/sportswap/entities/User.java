package com.skilldistillery.sportswap.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User {

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private boolean active;

	private String role;

	private String email;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime updated;

	private LocalDateTime deactivated;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address userAddress;

	@OneToMany(mappedBy = "sender")
	private List<Message> sentMessages;

	@OneToMany(mappedBy = "sender")
	private List<Message> receivedMessages;

	@OneToMany(mappedBy = "user")
	private List<DonationListing> donationListings;

	@OneToMany(mappedBy = "sellingUser")
	private List<SaleListing> saleListings;

	@OneToMany(mappedBy = "swappingUser")
	private List<SwapListing> swapListings;

	@OneToMany(mappedBy = "userItem")
	private List<Item> usersItems;

	@OneToMany(mappedBy = "postingUser")
	private List<Post> posts;
	
	@ManyToMany(mappedBy="userSports",fetch=FetchType.EAGER)
	private List<Sport> favoriteSports;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;

	}

	public void addSentMessage(Message message) {
		if (sentMessages == null) {
			sentMessages = new ArrayList<>();
		}
		if (!sentMessages.contains(message)) {

			sentMessages.add(message);
			if (message.getSender() != null) {
				message.getSender().removeSentMessage(message);
			}
			message.setSender(this);
		}
	}

	public void removeSentMessage(Message message) {
		if (sentMessages != null && sentMessages.contains(message)) {
			sentMessages.remove(message);
			message.setSender(null);
		}
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public void addReceivedMessage(Message message) {
		if (receivedMessages == null) {
			receivedMessages = new ArrayList<>();
		}
		if (!receivedMessages.contains(message)) {

			receivedMessages.add(message);
			if (message.getReceiver() != null) {
				message.getReceiver().removeReceivedMessage(message);
			}
			message.setReceiver(this);
		}
	}

	public void removeReceivedMessage(Message message) {
		if (receivedMessages != null && receivedMessages.contains(message)) {
			receivedMessages.remove(message);
			message.setReceiver(null);
		}
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address address) {
		this.userAddress = address;
	}

	public List<DonationListing> getDonationListings() {
		return donationListings;
	}

	public void setDonationListings(List<DonationListing> donationListings) {
		this.donationListings = donationListings;
	}

	public void addDonationListing(DonationListing listing) {
		if (donationListings == null) {
			donationListings = new ArrayList<>();
		}
		if (!donationListings.contains(listing)) {

			donationListings.add(listing);
			if (listing.getUser() != null) {
				listing.getUser().removeDonationListing(listing);
			}
			listing.setUser(this);
		}
	}

	public void removeDonationListing(DonationListing listing) {
		if (donationListings != null && donationListings.contains(listing)) {
			donationListings.remove(listing);
			listing.setUser(null);
		}
	}

	public List<SaleListing> getSaleListings() {
		return saleListings;
	}

	public void setSaleListings(List<SaleListing> saleListings) {
		this.saleListings = saleListings;
	}

	public void addSaleListing(SaleListing listing) {
		if (saleListings == null) {
			saleListings = new ArrayList<>();
		}
		if (!saleListings.contains(listing)) {

			saleListings.add(listing);
			if (listing.getSellingUser() != null) {
				listing.getSellingUser().removeSaleListing(listing);
			}
			listing.setSellingUser(this);
		}
	}

	public void removeSaleListing(SaleListing listing) {
		if (saleListings != null && saleListings.contains(listing)) {
			saleListings.remove(listing);
			listing.setSellingUser(null);
		}
	}

	public List<SwapListing> getSwapListings() {
		return swapListings;
	}

	public void setSwapListings(List<SwapListing> swapListings) {
		this.swapListings = swapListings;
	}

	public void addSwapListing(SwapListing listing) {
		if (swapListings == null) {
			swapListings = new ArrayList<>();
		}
		if (!swapListings.contains(listing)) {

			swapListings.add(listing);
			if (listing.getSwappingUser() != null) {
				listing.getSwappingUser().removeSwapListing(listing);
			}
			listing.setSwappingUser(this);
		}
	}

	public void removeSwapListing(SwapListing listing) {
		if (swapListings != null && swapListings.contains(listing)) {
			swapListings.remove(listing);
			listing.setSwappingUser(null);
		}
	}

	public List<Item> getUsersItems() {
		return usersItems;
	}

	public void setUsersItems(List<Item> usersItems) {
		this.usersItems = usersItems;
	}

	public void addUsersItem(Item item) {
		if (usersItems == null) {
			usersItems = new ArrayList<>();
		}
		if (!usersItems.contains(item)) {

			usersItems.add(item);
			if (item.getUserItem() != null) {
				item.getUserItem().removeUsersItem(item);
			}
			item.setUserItem(this);
		}
	}

	public void removeUsersItem(Item item) {
		if (usersItems != null && usersItems.contains(item)) {
			usersItems.remove(item);
			item.setUserItem(null);
		}
	}
	


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addUsersPost(Post post) {
		if (posts == null) {
			posts = new ArrayList<>();
		}
		if (!posts.contains(post)) {

			posts.add(post);
			if (post.getPostingUser() != null) {
				post.getPostingUser().removeUsersPost(post);
			}
			post.setPostingUser(this);
		}
	}

	public void removeUsersPost(Post post) {
		if (posts != null && posts.contains(post)) {
			posts.remove(post);
			post.setPostingUser(null);
		}
	}
	
	

	public List<Sport> getFavoriteSports() {
		return favoriteSports;
	}

	public void setFavoriteSports(List<Sport> favoriteSports) {
		this.favoriteSports = favoriteSports;
	}
	
	public void addSport(Sport sport) {
		if(favoriteSports ==null) {
			favoriteSports = new ArrayList<>();
			}
			if(!favoriteSports.contains(sport)) {
				favoriteSports.add(sport);
				sport.addUserFavoriteSport(this);
				}
			}
	
	public void removeSport(Sport sport) {
		if(favoriteSports != null && favoriteSports.contains(sport)) {
			favoriteSports.remove(sport);
			sport.removeUserFavoriteSport(this);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active + ", role="
				+ role + ", email=" + email + ", created=" + created + ", updated=" + updated + ", deactivated="
				+ deactivated + "]";
	}

}
