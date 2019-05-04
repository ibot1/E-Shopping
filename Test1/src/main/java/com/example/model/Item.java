package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false, unique = true)
	private String uniqueId; 
	private Integer quantityInStock;
	private Double priceForEach;

	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "item", orphanRemoval = true)
	private List<Request> requests = new ArrayList<>();

	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "item", orphanRemoval = true)
	private Inventory inventory;
	
	public Item(){} 
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public Double getPriceForEach() {
		return priceForEach;
	}
	public void setPriceForEach(Double priceForEach) {
		this.priceForEach = priceForEach;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}
