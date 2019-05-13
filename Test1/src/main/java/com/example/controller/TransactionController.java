package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Request;
import com.example.service.TransactionServices;

@RestController
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private TransactionServices transactionServices;
	
	@Autowired
	private ItemsController itemsController;
	
	
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
		itemsController.updateRecords();
		return transactionServices.getFileNames();
	}
	
	@PostMapping("/transaction/confirm/{email}")
	public void confirmTransaction(@PathVariable String email) {
		transactionServices.confirmTransaction(email);
	}
	
	@GetMapping("/transaction/all/{email}")
	public ResponseEntity<?> getAllTransactions(@PathVariable String email){ 
		return new ResponseEntity<List<Request>>(transactionServices.getAllTransactions(email), HttpStatus.OK);
	}

}
