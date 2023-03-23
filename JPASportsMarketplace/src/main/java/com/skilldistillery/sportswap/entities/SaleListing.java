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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name= "sale_listing")
public class SaleListing {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double price;
	
	private boolean active;
	
	private String title;
	
	private String description;
	
	@CreationTimestamp
	private LocalDateTime created;
	
	@UpdateTimestamp
	private LocalDateTime updated;
	
	private LocalDateTime deactivated;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User sellingUser;
	
	@OneToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	@OneToMany(mappedBy= "saleListing")
	private List<Post> saleListingPosts;
	
	public SaleListing() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	

	public User getSellingUser() {
		return sellingUser;
	}

	public void setSellingUser(User sellingUser) {
		this.sellingUser = sellingUser;
	}
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Post> getSaleListingPosts() {
		return saleListingPosts;
	}

	public void setSaleListingPosts(List<Post> saleListingPosts) {
		this.saleListingPosts = saleListingPosts;
	}
	
	public void addSaleListingPost(Post salePost) {
		if (saleListingPosts == null) {
			saleListingPosts = new ArrayList<>();
		}
		if (!saleListingPosts.contains(salePost)) {

			saleListingPosts.add(salePost);
			if (salePost.getSaleListing() != null) {
				salePost.getSaleListing().removeSaleListingPost(salePost);
			}
			salePost.setSaleListing(this);
		}
	}

	public void removeSaleListingPost(Post salePost) {
		if (saleListingPosts != null && saleListingPosts.contains(salePost)) {
			saleListingPosts.remove(salePost);
			salePost.setSaleListing(null);
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
		SaleListing other = (SaleListing) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "SaleListing [id=" + id + ", price=" + price + ", active=" + active + ", created=" + created
				+ ", updated=" + updated + ", deactivated=" + deactivated + "]";
	}
	
	

}
