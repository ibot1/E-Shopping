package com.example.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false, unique = true)
	private String uniqueId; 
	private Integer quantityInStock;
	private Integer priceForEach;

	
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
	public Integer getPriceForEach() {
		return priceForEach;
	}
	public void setPriceForEach(Integer priceForEach) {
		this.priceForEach = priceForEach;
	}


	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}
