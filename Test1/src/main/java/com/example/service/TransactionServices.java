package com.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Request;
import com.example.repository.ItemRepository;
import com.example.repository.RequestRepository;

@Service
public class TransactionServices {
	
	public static Map<String, Integer> cart = new HashMap<String, Integer>();
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	public List<String> stringToJson(String tmp){
		List<String> tmp1 = new ArrayList<String>();
        String key = "", value = "";
        int ctr = 0;
      
        for(int i = 1; i < tmp.length() -1; i++){    
        	if(tmp.charAt(i) == '"' || tmp.charAt(i) == ' ')	continue;
            if(tmp.charAt(i) == ':') { ctr = 2;continue;}
            if(tmp.charAt(i-1) == '{' && ctr == 0) {key += tmp.charAt(i); continue;}
            if(ctr == 0)	key += tmp.charAt(i);
            if(ctr == 2)	value += tmp.charAt(i);
        }
        tmp1.add(key);tmp1.add(value);
        return tmp1;
    }
	
	public void postData(String data) {
		List<String> tmp1 = stringToJson(data);
		cart.put(tmp1.get(0), Integer.valueOf(tmp1.get(1)));	
	}
	
	public Map<?,?> checkout() {
		return cart;
	}
	
	public String getFileNames() {
		File folder = new File("S:\\Spring Boot\\React_Practise\\Practise1\\react-app\\src\\images");
		File[] fileList = folder.listFiles();
		String filenames = "";
		
		for(int i = 0; i < fileList.length; i++)	filenames += fileList[i].getName() + ",";
		
		return filenames.substring(0, filenames.length() - 1);
	}
	
	String findKey(String key) {
		String tmp = "";
		int ctr = 0;
		for(int i = 0; i < key.length(); i++) {
			if(key.charAt(i) == '_')	ctr++;
			if(ctr == 2)	break;
			if(key.charAt(i) != '_')	tmp += key.charAt(i);
		}
		return tmp;
	}
	
	public void confirmTransaction(String email) {
		
		String id;
		
		for(String key : cart.keySet()) {
			if(cart.get(key) > 0) {
				Request request = new Request();
				id = findKey(key).toLowerCase();
				Integer price = itemRepository.findByUniqueId(id).getPriceForEach();
				request.setPrice(price);
				request.setQuantity(cart.get(key));
				request.setItemName(id);
				request.setEmail(email);
				System.out.println(cart);
				requestRepository.save(request);
			}else	continue;
		}
		cart =  new HashMap<String, Integer>();
	}
	
	public List<Request> getAllTransactions(String email){
		return requestRepository.findAllByEmail(email);
	}
}
//something good to do with transaction=id like search
//-javaagent:S:\AppServerAgent-4.5.9.25648\ver4.5.9.25648\javaagent.jar
