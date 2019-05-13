package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.ItemRepository;

@Service
public class SearchServices {
	
	@Autowired
	ItemRepository itemRepository; 
	
	
	
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
