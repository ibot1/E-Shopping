package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ItemService;

@RestController
public class ItemsController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/update")
	void updateRecords() {
		itemService.addItems();
	}
}
