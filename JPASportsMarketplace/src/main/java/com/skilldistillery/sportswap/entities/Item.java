package com.skilldistillery.sportswap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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