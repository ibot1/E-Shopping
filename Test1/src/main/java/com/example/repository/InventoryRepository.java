package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}
