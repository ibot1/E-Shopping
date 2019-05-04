package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Item;
import com.example.repository.ItemRepository;

@Service
public class SearchServices {
	
	@Autowired
	ItemRepository itemRepository; 
	
	public List<Item> searchByDealId(String uniqueId) {
		return itemRepository.findAllByUniqueId(uniqueId);
	}
	
}

/* 
 * Do this after personalizing the service [create an account for each user, track their purchase history]
 * Most favorite purchases
 * 
 *
 *
 *Search + Advance Filter option --Last
 *Multiple-query search
 */
