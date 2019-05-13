package com.example.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double accountBalance;
	
	@Column(updatable = false, unique = true)
	@NotBlank(message = "Email can't be blank or empty")
	private String email;
	
	@Column(updatable = true)
	@Transient
	@NotBlank(message = "Password can't be blank or empty")
	private String password;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date createdAt;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private UserInfo userInfo;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date lastLoggedin;
	
	
	
	
	public User() {
		this.setLastLoggedin();
	}
	
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return "User [accountBalance=" + accountBalance + ", email=" + email + ", password=" + password + ", createdAt="
				+ createdAt + "]";
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getLastLoggedin() {
		return lastLoggedin;
	}

	public void setLastLoggedin() {
		this.lastLoggedin = new Date();
	}	
}
