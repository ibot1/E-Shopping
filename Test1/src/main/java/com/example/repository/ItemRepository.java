package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{
	
	Item findByUniqueId(String uniqueId);
}
