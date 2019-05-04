package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Inventory {
	
	public Inventory() {}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	Integer inStock;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	Item item;
	
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	Date lastRestocked;

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	public Date getLastRestocked() {
		return lastRestocked;
	}
	
	@PreUpdate
	public void setLastRestocked() {
		this.lastRestocked = new Date();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setLastRestocked(Date lastRestocked) {
		this.lastRestocked = lastRestocked;
	}
}
