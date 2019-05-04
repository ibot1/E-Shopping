package com.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TransactionServices {
	
	public static Map<String, Integer> cart = new HashMap<String, Integer>();
	
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
}
//-javaagent:S:\AppServerAgent-4.5.9.25648\ver4.5.9.25648\javaagent.jar
