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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	public User() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private boolean active;
	
	private String role;

	private String email;
	
	private LocalDateTime created; 
	
	private LocalDateTime updated;

	private LocalDateTime deactivated;
	
	@OneToOne
	@JoinColumn (name= "address_id")
	private Address userAddress;
	
	@OneToMany (mappedBy= "sender")
	private List<Message> sentMessages;
	
	@OneToMany (mappedBy= "sender")
	private List<Message> receivedMessages;
	
	@OneToMany (mappedBy= "user")
	private List<DonationListing> donationListings;
	
	
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
		if (sentMessages == null ) {sentMessages = new ArrayList<>();}
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
		if (receivedMessages == null ) {receivedMessages = new ArrayList<>();}
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
