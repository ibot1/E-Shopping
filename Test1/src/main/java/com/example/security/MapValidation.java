package com.example.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidation {
	
	public ResponseEntity<?> mapValidationErrors(BindingResult result){
		Map<String,String> errFields = new HashMap<String, String>();
		
		if(result.hasErrors()) {
			for(FieldError err : result.getFieldErrors()) {
				errFields.put(err.getField(), err.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errFields, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
