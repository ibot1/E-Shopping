package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TransactionServices;

@RestController
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private TransactionServices transactionServices;
	
	
	@GetMapping("/")
	public String home(){
		return "{kjhh:jf, hgh:ik, pp:op}";
	}
	
	@PostMapping("/posts/{data}")
	public void postData(@PathVariable String data) {
		transactionServices.postData(data);	
	}
	
	@GetMapping("/checkout")
	public String checkout(){
		return transactionServices.checkout().toString();
	}
	
	@GetMapping("/filenames")
	public String getFileNames() {
		return transactionServices.getFileNames();
	}

}
