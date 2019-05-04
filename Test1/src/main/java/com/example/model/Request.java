package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore; 

@Entity
public class Request {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Item item;
	
	private Integer quantity;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date datePurchased;
	
	public Request(){}
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}


	public Date getDatePurchased() {
		return datePurchased;
	}
	
	@PrePersist
	public void setDatePurchased() {
		this.datePurchased = new Date();
	}


	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}
}

//consider including transaction-id