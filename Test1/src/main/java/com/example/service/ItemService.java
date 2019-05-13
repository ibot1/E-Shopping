package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Item;
import com.example.repository.ItemRepository;


@Service
public class ItemService {
	
	@Autowired
	TransactionServices transactionServices; 
	
	@Autowired
	ItemRepository itemRepository;
	
	public void addItems() {
		String filenames = transactionServices.getFileNames() + ",";
		
		String tmp = "";
		
		
		for(int i = 0; i < filenames.length(); i++) {
			if(filenames.charAt(i) != ',')  tmp += filenames.charAt(i);
			else {
				
				List<String> tmp1 = formatFileNames(tmp);
				//System.out.println("Filesnames " + filenames);System.out.println(tmp + " " + tmp1.toString() );
				
				Item item = itemRepository.findByUniqueId(tmp1.get(0).toLowerCase() + tmp1.get(1).toLowerCase());
				if(item != null) {
					continue;
				}else {
					item = new Item();
					item.setPriceForEach(Integer.valueOf(tmp1.get(2)));
					//be careful of duplicating the stock number [hint use try-catch]
					item.setQuantityInStock(Integer.valueOf(tmp1.get(3)));
					item.setUniqueId(tmp1.get(0).toLowerCase() + tmp1.get(1).toLowerCase());
					itemRepository.save(item);
				}
				tmp = "";
			}
			
		}
	}
	
	public List<String> formatFileNames(String filename) {
		//System.out.println(filename);
		List<String> tmp = new ArrayList<String>();
		String tmp1 = "";
		
		for(int i = 0; i < filename.length(); i++) {
			if(filename.charAt(i) != '_') tmp1 += filename.charAt(i);
			else {tmp.add(tmp1); tmp1 = "";}
		}
		tmp.add(tmp1.substring(0, tmp1.length() - 4));
		
		//System.out.println(filename + " " + tmp);
		
		return tmp;
	}
}



//Remember to fix the getFileNames() from String to a List