package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{
	
	List<Item> findAllByUniqueId(String uniqueId);
}
