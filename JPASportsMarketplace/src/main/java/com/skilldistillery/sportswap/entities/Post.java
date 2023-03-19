package com.skilldistillery.sportswap.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comment;

	private boolean active;

	@CreationTimestamp
	private LocalDateTime created;

	@UpdateTimestamp
	private LocalDateTime updated;

	private LocalDateTime deactivated;

	@ManyToOne
	@JoinColumn(name = "donation_listing_id")
	private DonationListing donationListing;

	@ManyToOne
	@JoinColumn(name = "swap_listing_id")
	private SwapListing swapListing;

	@ManyToOne
	@JoinColumn(name = "sale_listing_id")
	private SaleListing saleListing;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User postingUser;

	@OneToMany(mappedBy = "originalPost")
	private List<Post> replies;

	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private Post originalPost;

	public Post() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public DonationListing getDonationListing() {
		return donationListing;
	}

	public void setDonationListing(DonationListing donationListing) {
		this.donationListing = donationListing;
	}

	public SwapListing getSwapListing() {
		return swapListing;
	}

	public void setSwapListing(SwapListing swapListing) {
		this.swapListing = swapListing;
	}

	public SaleListing getSaleListing() {
		return saleListing;
	}

	public void setSaleListing(SaleListing saleListing) {
		this.saleListing = saleListing;
	}

	public User getPostingUser() {
		return postingUser;
	}

	public void setPostingUser(User postingUser) {
		this.postingUser = postingUser;
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
		Post other = (Post) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", comment=" + comment + ", active=" + active + ", created=" + created + ", updated="
				+ updated + ", deactivated=" + deactivated + "]";
	}

}