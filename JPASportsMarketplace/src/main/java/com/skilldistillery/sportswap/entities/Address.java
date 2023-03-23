package com.skilldistillery.sportswap.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="address")
	private String street;
	
	@Column(name="address2")
	private String street2;
	
	private String city;
	
	@Column(name ="state_province")
	private String state;
	
	@Column(name= "postal_code")
	private String postalCode;
	
	@Column(name = "country_code")
	private String countryCode;
	
//	@OneToMany(mappedBy= "donationAddress")
//	private List<DonationListing> donationListings;
//	
//	@OneToMany(mappedBy= "swapAddress")
//	private List<SwapListing> swapListings;
	
	public Address() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
		Address other = (Address) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return  "Street Address: " + street + " APT/Suite: " + street2 + " City: " + city + " State: "
				+ state + " ZipCode: " + postalCode + " Country: " + countryCode ;
	}
	
	
}
