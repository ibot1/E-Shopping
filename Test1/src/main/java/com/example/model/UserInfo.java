package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserInfo {
	
	public UserInfo() {}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "First Name can't be blank")
	private String fname = "#";
	@NotBlank(message = "Last Name can't be blank")
	private String lname = "#";
	@NotBlank(message = "Address1 can't be blank")
	private String address1 = "#";
	@NotBlank(message = "City can't be blank")
	private String city = "#";
	@NotBlank(message = "State can't be blank")
	private String state = "#";
	@NotBlank(message = "Zipcode can't be blank")
	private String zipcode = "#";
	@NotBlank(message = "Phone-Number can't be blank")
	private String phoneNum = "#";
	@NotBlank(message = "Email can't be blank")
	private String email = "#";
	
	@NotBlank(message = "Country can't be blank")
	private String country = "#";
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date lastUpdated;
		
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	@JsonIgnore
	private User user;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated() {
		this.lastUpdated = new Date();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address1=" + address1 + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", country=" + country + ", lastUpdated=" + lastUpdated + "]";
	}

	
	public void mergeUpdate(UserInfo userInfoNew) {
		this.setLastUpdated();
		if(this.getAddress1() != userInfoNew.getAddress1())	this.setAddress1(userInfoNew.getAddress1());
		if(this.getCity() != userInfoNew.getCity())		this.setCity(userInfoNew.getCity());
		if(this.getCountry() != userInfoNew.getCountry())	this.setCountry(userInfoNew.getCountry());
		if(this.getEmail() != userInfoNew.getEmail())	this.setEmail(userInfoNew.getEmail());
		if(this.getFname() != userInfoNew.getFname())	this.setFname(userInfoNew.getFname());
		if(this.getLname() != userInfoNew.getLname())	this.setLname(userInfoNew.getLname());
		if(this.getPhoneNum() != userInfoNew.getPhoneNum())		this.setPhoneNum(userInfoNew.getPhoneNum());
		if(this.getZipcode() != userInfoNew.getZipcode())	this.setZipcode(userInfoNew.getZipcode());
		if(this.getState() != userInfoNew.getState())	this.setState(userInfoNew.getState());
	}
}
